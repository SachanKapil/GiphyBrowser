package com.giphybrowser

import android.app.Application
import android.content.Context


/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

class GiphyBrowserApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}
