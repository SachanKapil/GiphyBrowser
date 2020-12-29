package com.giphybrowser.data.model

import com.giphybrowser.data.model.meta.MetaBean
import com.giphybrowser.data.model.pagination.PaginationBean
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by {Kapil Sachan} on 18/11/2020.
 */

data class BaseResponse<T>(

    @SerializedName("pagination")
    @Expose
    var paginationBean: PaginationBean? = null,
    @SerializedName("meta")
    @Expose
    val metaBean: MetaBean? = null,
    @SerializedName("data")
    @Expose
    val data: T? = null
)