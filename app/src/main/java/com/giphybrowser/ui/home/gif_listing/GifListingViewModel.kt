package com.giphybrowser.ui.home.gif_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.giphybrowser.BuildConfig
import com.giphybrowser.constants.AppConstants
import com.giphybrowser.data.model.Event
import com.giphybrowser.data.model.WrappedResponse
import com.giphybrowser.data.model.gif.GetGifListRequest
import com.giphybrowser.data.model.gif.GifBean

class GifListingViewModel : ViewModel() {

    private val repo = GifListingRepo()

    private val gifListRequestLiveData = MutableLiveData<GetGifListRequest>()
    private val gifListResponseLiveData =
        Transformations.switchMap(gifListRequestLiveData) { request ->
            repo.hitGetGifListApi(
                request
            )
        }

    fun getGifListResponseLiveData(): LiveData<Event<WrappedResponse<ArrayList<GifBean>>>> {
        return gifListResponseLiveData
    }

    fun hitGetGifListApi(pageNumber: Int) {
        val getGifListRequest = GetGifListRequest(
            BuildConfig.GIPHY_API_KEY,
            AppConstants.ValueConstants.PAGINATION_ITEM_LIMIT,
            pageNumber * 25
        )
        gifListRequestLiveData.value = getGifListRequest
    }
}