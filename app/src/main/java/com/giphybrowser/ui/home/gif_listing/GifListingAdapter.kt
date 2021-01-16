package com.giphybrowser.ui.home.gif_listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.giphybrowser.R
import com.giphybrowser.constants.AppConstants
import com.giphybrowser.data.model.gif.GifBean
import com.giphybrowser.databinding.LayoutGifItemBinding
import com.giphybrowser.databinding.LayoutRvFooterLoaderBinding
import com.giphybrowser.utils.ViewUtils
import com.giphybrowser.utils.gone
import com.giphybrowser.utils.visible


class GifListingAdapter(private val listener: GigListAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var paginationFooterState = AppConstants.PaginationFooterState.HIDDEN
    private var gifList = ArrayList<GifBean>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return when (position) {
            AppConstants.ViewTypeConstants.VIEW_TYPE_NORMAL -> ViewHolderGif(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_gif_item, parent, false
                )
            )
            AppConstants.ViewTypeConstants.VIEW_TYPE_LOADING -> FooterLoaderViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_rv_footer_loader, parent, false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderGif -> {
                holder.bind(gifList[position])
            }
            is FooterLoaderViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (paginationFooterState != AppConstants.PaginationFooterState.HIDDEN && position == gifList.size - 1)
            AppConstants.ViewTypeConstants.VIEW_TYPE_LOADING
        else {
            AppConstants.ViewTypeConstants.VIEW_TYPE_NORMAL
        }
    }

    fun addLoading() {
        if (paginationFooterState == AppConstants.PaginationFooterState.HIDDEN) {
            paginationFooterState = AppConstants.PaginationFooterState.PROGRESS
            gifList.add(GifBean())
            notifyItemInserted(gifList.size - 1)
        }
    }

    fun removeLoading() {
        if (paginationFooterState != AppConstants.PaginationFooterState.HIDDEN) {
            paginationFooterState = AppConstants.PaginationFooterState.HIDDEN
            val position: Int = gifList.size - 1
            gifList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clearAllData() {
        paginationFooterState = AppConstants.PaginationFooterState.HIDDEN
        gifList.clear()
        notifyDataSetChanged()
    }

    fun updateLoading(state: Int) {
        paginationFooterState = state
        notifyItemChanged(gifList.size - 1)
    }

    fun getLoadingStatus(): Int {
        return paginationFooterState
    }

    fun getList(): ArrayList<GifBean> {
        return gifList
    }

    fun loadData(currentPageNumber: Int, list: ArrayList<GifBean>) {
        removeLoading()
        if (currentPageNumber == 0) {
            gifList.clear()
        }
        gifList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolderGif(val binding: LayoutGifItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gifBean: GifBean) {
            ViewUtils.loadGif(
                binding.ivGif,
                binding.pbLoading,
                R.drawable.bg_grey_side_rounded_rectangle,
                gifBean.images?.previewGif?.url ?: ""
            )
            binding.root.setOnClickListener {
                listener.onGifClick(gifBean)
            }
        }
    }

    inner class FooterLoaderViewHolder(val binding: LayoutRvFooterLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            if (paginationFooterState == AppConstants.PaginationFooterState.PROGRESS) {
                binding.pbFooter.visible()
                binding.ivRetryFooter.gone()
            } else if (paginationFooterState == AppConstants.PaginationFooterState.RETRY) {
                binding.pbFooter.gone()
                binding.ivRetryFooter.visible()
            }

            binding.ivRetryFooter.setOnClickListener {
                listener.openFooterRetryClick()
            }
        }
    }

    interface GigListAdapterListener {
        fun onGifClick(gifBean: GifBean)
        fun openFooterRetryClick()
    }
}