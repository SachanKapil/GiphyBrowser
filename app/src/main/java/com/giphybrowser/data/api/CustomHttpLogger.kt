package com.giphybrowser.data.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by {Kapil Sachan} on 18/11/2020.
 */

class CustomHttpLogger : HttpLoggingInterceptor.Logger {

    private val logName = "GiphyBrowser"

    override fun log(message: String) {
        if (!message.startsWith("{")) {
            Log.d(logName, message)
            return
        }
        try {
            val prettyPrintJson = GsonBuilder().setPrettyPrinting().create().toJson(
                JsonParser().parse(message)
            )
            Log.d(logName, prettyPrintJson)
        } catch (m: JsonSyntaxException) {
            Log.d(logName, message)
        }
    }
}