package io.webt.webthingsdashboard
import android.util.JsonReader
import okhttp3.Request
import okio.IOException
import org.json.JSONArray

/*
 *Object representing a things in the webthings.io gateway
 *
 * id -- id of the things
 * name -- name of the things(title)
 * wtioGw -- gateway who own this thing
 */

class WebtioThings (val id: String, var name: String, val wtioGw: WebtioGateway){
    private val BASE_URL = wtioGw.BASE_URL
    private val client = wtioGw.client
    private val TOKEN = wtioGw.TOKEN


    private fun getData(): JSONArray{
        val request = Request.Builder()
            .url(BASE_URL)
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer $TOKEN").build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful){
            throw IOException("Unexpected code $response")
        }
        val data = response.body!!.string()
        return JSONArray(data)
    }


    fun getRawData(): Any{
        var rawData = wtioGw.asyncCall(::getData)

        return rawData
    }
}