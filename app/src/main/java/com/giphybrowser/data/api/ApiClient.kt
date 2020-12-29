package com.giphybrowser.data.api

import com.giphybrowser.data.model.BaseResponse
import com.giphybrowser.data.model.gif.GifBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

interface ApiClient {

    @GET("trending")
    fun hitGetGifListApi(
        @Query("api_key") apiKey: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): Call<BaseResponse<ArrayList<GifBean>>>

}