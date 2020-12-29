package com.giphybrowser.ui.home.gif_listing

import androidx.lifecycle.MutableLiveData
import com.giphybrowser.base.NetworkCallback
import com.giphybrowser.data.api.ApiManager
import com.giphybrowser.data.model.Event
import com.giphybrowser.data.model.FailureResponse
import com.giphybrowser.data.model.WrappedResponse
import com.giphybrowser.data.model.gif.GetGifListRequest
import com.giphybrowser.data.model.gif.GifBean
import com.giphybrowser.data.model.meta.MetaBean
import com.giphybrowser.data.model.pagination.PaginationBean

class GifListingRepo {

    internal fun hitGetGifListApi(gifListRequest: GetGifListRequest): MutableLiveData<Event<WrappedResponse<ArrayList<GifBean>>>> {

        val gifListResponseLiveData =
            MutableLiveData<Event<WrappedResponse<ArrayList<GifBean>>>>()
        val wrappedResponse = WrappedResponse<ArrayList<GifBean>>()

        ApiManager.hitGetGifListApi(gifListRequest)
            .enqueue(object : NetworkCallback<ArrayList<GifBean>>() {
                override fun onSuccess(
                    t: ArrayList<GifBean>?,
                    paginationBean: PaginationBean?,
                    metaBean: MetaBean?
                ) {
                    wrappedResponse.data = t
                    wrappedResponse.paginationBean = paginationBean
                    wrappedResponse.metaBean = metaBean
                    gifListResponseLiveData.value = Event(wrappedResponse)
                }

                override fun onFailure(failureResponse: FailureResponse) {
                    wrappedResponse.failureResponse = failureResponse
                    gifListResponseLiveData.value = Event(wrappedResponse)
                }

                override fun onError(t: Throwable) {
                    wrappedResponse.failureResponse = FailureResponse.genericError()
                    gifListResponseLiveData.value = Event(wrappedResponse)
                }
            })

        return gifListResponseLiveData
    }

}