package com.giphybrowser.data.model.meta

import com.google.gson.annotations.SerializedName

data class MetaBean(
    @SerializedName("msg")
    var msg: String? = null,
    @SerializedName("response_id")
    var responseId: String? = null,
    @SerializedName("status")
    var status: Int? = null
)