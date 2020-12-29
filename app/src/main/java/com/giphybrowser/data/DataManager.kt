package com.giphybrowser.data

import com.airhireme.data.preferences.PreferenceManager
import com.giphybrowser.constants.AppConstants
import com.giphybrowser.data.model.gif.GifBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

object DataManager {

    fun clearAllPreferenceData() {
        PreferenceManager.clearAllPrefs()
    }

    fun saveGifListingData(gifList: ArrayList<GifBean>?) {
        val list = Gson().toJson(gifList)
        PreferenceManager.putString(AppConstants.PreferenceConstantsKeys.KEY_GIF_LIST, list)
    }

    fun getGifListingData(): ArrayList<GifBean>? {
        val type: Type = object : TypeToken<ArrayList<GifBean>>() {}.type
        val forumDataList: String? =
            PreferenceManager.getString(AppConstants.PreferenceConstantsKeys.KEY_GIF_LIST)
        return Gson().fromJson(forumDataList, type)
    }

    fun clearGifListData() {
        PreferenceManager.clearViaKey(AppConstants.PreferenceConstantsKeys.KEY_GIF_LIST)
    }
}