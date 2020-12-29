package com.giphybrowser.data.model.pagination

import com.google.gson.annotations.SerializedName

data class PaginationBean(
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("offset")
    var offset: Int? = null,
    @SerializedName("total_count")
    var totalCount: Int? = null
)