package com.giphybrowser.ui.home

import android.os.Bundle
import com.giphybrowser.R
import com.giphybrowser.base.BaseActivity
import com.giphybrowser.data.model.gif.GifBean
import com.giphybrowser.databinding.ActivityHomeBinding
import com.giphybrowser.ui.home.gif_full_view.GifFullViewFragment
import com.giphybrowser.ui.home.gif_listing.GifListingFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(),
    GifListingFragment.IGifListingsFragmentHost {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openGifListingFragment()
    }

    private fun openGifListingFragment() {
        replaceFragment(
            R.id.fl_main, GifListingFragment.getInstance(),
            GifListingFragment::class.java.simpleName
        )
    }

    override fun openGifFullViewFragment(gifBean: GifBean) {
        addFragmentWithBackStackWithAnimation(
            R.id.fl_main, GifFullViewFragment.getInstance(gifBean.images?.original?.url),
            GifFullViewFragment::class.java.simpleName
        )
    }
}