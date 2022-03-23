package io.webt.webthingsdashboard
import okhttp3.Request
import okio.IOException
import org.json.JSONObject

/*
 *Object representing a things in the webthings.io gateway
 *
 * id -- id of the things
 * name -- name of the things(title)
 * wtioGw -- gateway who own this thing
 */

class WebtioThings (id: String, var name: String, val wtioGw: WebtioGateway){
    internal val BASE_URL = "${wtioGw.BASE_URL}/${id}"
    internal val client = wtioGw.client
    internal val TOKEN = wtioGw.TOKEN

    var thingProperties: MutableMap<String, WebtioProperty> = mutableMapOf()//list of things object

    private fun getData(): JSONObject{
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
        return JSONObject(data)
    }

    fun getRawData(): Any? {
        if (!this.wtioGw.isAvailable(this.wtioGw.HOST)) {
            println("Gateway Unavailable")
            return null
        }
        return wtioGw.asyncCall(::getData)
    }

    fun initProperties(){
        val data = wtioGw.asyncCall(::getData) as JSONObject
        val properties: JSONObject = data.get("properties") as JSONObject
        for (property in properties.keys()){
            val thingProperty = properties.get(property) as JSONObject
            thingProperties.put(thingProperty.get("name") as String, WebtioProperty(thingProperty.get("name") as String, thingProperty.get("title") as String, this, thingProperty))

        }
    }
}