package com.giphybrowser.ui.home.gif_full_view

import android.os.Bundle
import android.view.View
import com.airhireme.base.BaseFragment
import com.giphybrowser.R
import com.giphybrowser.constants.AppConstants
import com.giphybrowser.databinding.FragmentGifFullViewBinding
import com.giphybrowser.utils.ViewUtils

class GifFullViewFragment : BaseFragment<FragmentGifFullViewBinding>(), View.OnClickListener {
    private lateinit var binding: FragmentGifFullViewBinding

    companion object {
        fun getInstance(gifUrl: String?): GifFullViewFragment {
            val gifFullViewFragment = GifFullViewFragment()
            val bundle = Bundle().also {
                it.putString(AppConstants.BundleConstants.KEY_GIF_URL, gifUrl)
            }
            gifFullViewFragment.arguments = bundle
            return gifFullViewFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_gif_full_view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        setUpToolbar()
        initListener()
        getBundleData()
    }

    private fun setUpToolbar() {
        binding.appbar.tvTitle.text = getText(R.string.txt_gif_full_view)
    }

    private fun initListener() {
        binding.appbar.ivBack.setOnClickListener(this)
    }

    private fun getBundleData() {
        arguments?.let {
            it.getString(AppConstants.BundleConstants.KEY_GIF_URL)?.let { gifUrl ->
                loadGif(gifUrl)
            }
        }
    }

    private fun loadGif(url: String) {
        ViewUtils.loadGif(
            binding.ivGif,
            binding.pbLoading,
            R.drawable.bg_grey_side_rounded_rectangle,
            url
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.appbar.ivBack.id -> {
                requireActivity().onBackPressed()
            }
        }
    }
}