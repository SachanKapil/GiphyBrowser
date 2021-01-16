package com.giphybrowser.ui.home.gif_listing

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airhireme.base.BaseFragment
import com.giphybrowser.R
import com.giphybrowser.constants.AppConstants
import com.giphybrowser.custom_works.GridSpaceItemDecoration
import com.giphybrowser.data.DataManager
import com.giphybrowser.data.model.gif.GifBean
import com.giphybrowser.databinding.FragmentGifListingBinding
import com.giphybrowser.utils.AppUtils
import com.giphybrowser.utils.inVisible
import kotlinx.android.synthetic.main.layout_progress_bar.view.*

class GifListingFragment : BaseFragment<FragmentGifListingBinding>(),
    SwipeRefreshLayout.OnRefreshListener, GifListingAdapter.GigListAdapterListener,
    View.OnClickListener {
    private lateinit var binding: FragmentGifListingBinding
    private lateinit var host: IGifListingsFragmentHost
    private lateinit var adapter: GifListingAdapter
    private lateinit var viewModel: GifListingViewModel
    private var nextPageNumber = 0

    companion object {
        fun getInstance(): GifListingFragment {
            return GifListingFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_gif_listing
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IGifListingsFragmentHost) {
            host = context
        } else {
            throw IllegalStateException("host must implement IGifListingsFragmentHost")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = GifListingAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        setUpToolbar()
        initViewModel()
        initListener()
        initRecyclerView()
        initObservers()

        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.progressBarLayout.showLoading()
            hitGetGifListingApi()
        } else {
            //support for offline mode
            DataManager.getGifListingData()?.let { oldGifList ->
                nextPageNumber =
                    oldGifList.size.div(AppConstants.ValueConstants.PAGINATION_ITEM_LIMIT)
                adapter.loadData(nextPageNumber, oldGifList)
            } ?: let {
                binding.progressBarLayout.setErrorWithRetryButton(getString(R.string.message_no_internet_connection))
            }
        }
    }

    private fun setUpToolbar() {
        binding.appbar.ivBack.inVisible()
        binding.appbar.tvTitle.text = getText(R.string.txt_gif_list)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[GifListingViewModel::class.java]
    }

    private fun initListener() {
        binding.refreshLayout.setOnRefreshListener(this)
        binding.refreshLayout.setColorSchemeResources(R.color.orange)
        binding.progressBarLayout.btnTryAgain.setOnClickListener(this)
    }

    private fun initRecyclerView() {
        binding.rvGif.adapter = adapter

        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    AppConstants.ViewTypeConstants.VIEW_TYPE_NORMAL -> 1
                    AppConstants.ViewTypeConstants.VIEW_TYPE_LOADING -> 2
                    else -> 1
                }
            }
        }
        val space8Dp = AppUtils.convertDpToPixel(8f, requireContext()).toInt()
        binding.rvGif.addItemDecoration(
            GridSpaceItemDecoration(space8Dp * 2, space8Dp, space8Dp, space8Dp)
        )

        binding.rvGif.layoutManager = layoutManager

        binding.rvGif.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val pastVisibleItems: Int = layoutManager.findFirstVisibleItemPosition()
                if (nextPageNumber in 0..24 && dy > 0 && adapter.getLoadingStatus() == AppConstants.PaginationFooterState.HIDDEN && visibleItemCount + pastVisibleItems >= totalItemCount) {
                    adapter.addLoading()
                    if (AppUtils.isNetworkAvailable(requireContext())) {
                        hitGetGifListingApi()
                    } else {
                        adapter.updateLoading(AppConstants.PaginationFooterState.RETRY)
                    }
                }
            }
        })
    }


    private fun initObservers() {
        viewModel.getGifListResponseLiveData()
            .observe(viewLifecycleOwner, { wrappedResponseEvent ->
                if (wrappedResponseEvent != null && !wrappedResponseEvent.isAlreadyHandled) {
                    binding.progressBarLayout.hideLoading()
                    binding.refreshLayout.isRefreshing = false
                    val objectWrappedResponse = wrappedResponseEvent.getContent()
                    objectWrappedResponse?.failureResponse?.let {
                        onFailure(it)
                        if (nextPageNumber > 0) {
                            adapter.updateLoading(AppConstants.PaginationFooterState.RETRY)
                        } else {
                            binding.progressBarLayout.setErrorWithRetryButton(getString(R.string.message_something_went_wrong))
                        }
                    } ?: let {
                        objectWrappedResponse?.data?.let { gifList ->
                            if (gifList.size != 0) {
                                adapter.loadData(nextPageNumber, gifList)
                                DataManager.saveGifListingData(adapter.getList())
                            } else if (nextPageNumber == 0) {
                                adapter.clearAllData()
                                DataManager.clearGifListData()
                                binding.progressBarLayout.setErrorWithOutRetryButton(
                                    getString(R.string.message_no_data_found)
                                )
                            }
                            val totalCount = objectWrappedResponse.paginationBean?.totalCount
                            val count = objectWrappedResponse.paginationBean?.count
                            if (totalCount != null && count != null && totalCount.div(count) > nextPageNumber) {
                                //increase page number
                                nextPageNumber++
                            } else {
                                //stop pagination
                                nextPageNumber = -1
                            }
                        }
                    }
                }
            })
    }

    override fun onRefresh() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            hitGetGifListingApi(0)
        } else {
            binding.refreshLayout.isRefreshing = false
            showToastShort(getString(R.string.message_no_internet_connection))
        }
    }

    private fun hitGetGifListingApi(pageNumber: Int = nextPageNumber) {
        viewModel.hitGetGifListApi(pageNumber)
    }

    override fun onGifClick(gifBean: GifBean) {
        host.openGifFullViewFragment(gifBean)
    }

    override fun openFooterRetryClick() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            adapter.updateLoading(AppConstants.PaginationFooterState.PROGRESS)
            hitGetGifListingApi()
        } else {
            showToastShort(getString(R.string.message_no_internet_connection))
        }
    }

    interface IGifListingsFragmentHost {
        fun openGifFullViewFragment(gifBean: GifBean)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.progressBarLayout.btnTryAgain.id -> {
                if (AppUtils.isNetworkAvailable(requireContext())) {
                    binding.progressBarLayout.showLoading()
                    hitGetGifListingApi()
                } else {
                    showToastShort(getString(R.string.message_no_internet_connection))
                    binding.progressBarLayout.setErrorWithRetryButton(getString(R.string.message_no_internet_connection))
                }
            }
        }
    }
}