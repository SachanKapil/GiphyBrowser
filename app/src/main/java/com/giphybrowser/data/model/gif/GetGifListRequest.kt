package com.giphybrowser.data.model.gif

data class GetGifListRequest(
    var apiKey: String,
    var limit: Int,
    var offset: Int
)