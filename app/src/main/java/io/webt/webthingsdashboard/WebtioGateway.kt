package io.webt.webthingsdashboard

import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

/*
 *Webthings.io gateway object
 *
 * host -- String: url or ip of the webthings gateway
 * token -- String: web token genated in the gatway to avoid login with username and password
 */

class WebtioGateway(private val HOST: String,
                   private val TOKEN: String,
                   private val PORT: String,
                    private val SSL: Boolean = false) {

    private var client = OkHttpClient()
    private lateinit var BASE_URL: String;

    init {
        if (SSL){
            BASE_URL = "https://$HOST:$PORT/things"
        }else{
            BASE_URL = "http://$HOST:$PORT/things"
        }
    }

    fun getThings(): String {
        var request = Request.Builder()
            .url(BASE_URL)
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer $TOKEN").build()

        var response = client.newCall(request).execute()
        if (!response.isSuccessful){
            throw IOException("Unexpected code $response")
        }
        var data = response.body!!.string()
        return data
        }
}