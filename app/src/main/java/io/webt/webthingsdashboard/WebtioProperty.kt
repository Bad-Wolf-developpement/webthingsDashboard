package io.webt.webthingsdashboard
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject


/*
 *Object representing a property of a things in the webthings.io gateway
 *
 * name -- name of the property
 * title -- title of the property
 * wtioThings -- things who own this property
 */
@Parcelize
class WebtioProperty(val name: String,
                     var title: String,
                     private val wtioThings: @RawValue WebtioThings,
                     private val initialData: @RawValue JSONObject): Parcelable {
    private val BASE_URL = "${wtioThings.BASE_URL}/properties/${name}"
    private val client = wtioThings.client
    private val TOKEN = wtioThings.TOKEN
    private val HOST = this.wtioThings.wtioGw.HOST
    private var value: Any? = null
    private var visible: Any? = null
    private var minimum: Any? = null
    private var maximum: Any? = null
    private var type: Any? = null
    private var wotType: Any? = null
    private var unit: Any? = null
    private var readonly: Boolean = false

    init {
        if (initialData.has("value")){
            value = initialData.get("value")
        }
        if (initialData.has("visible")) {
            visible = initialData.get("visible")
        }
        if (initialData.has("minimum")) {
            minimum = initialData.get("minimum")
        }
        if (initialData.has("maximum")) {
            maximum = initialData.get("maximum")
        }
        if (initialData.has("type")) {
            type = initialData.get("type")
        }
        if (initialData.has("@type")) {
            wotType = initialData.get("@type")
        }
        if (initialData.has("unit")) {
            unit = initialData.get("unit")
        }
        if (initialData.has("readOnly")) {
            readonly = initialData.get("readOnly") as Boolean
        }
        //val links = initialData.get("links")
    }

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
    private fun pushData(url: String, name: String, value: Any){
        val JSON: MediaType? = "application/json".toMediaTypeOrNull()
        val sentData = "{\"$name\":$value}"
        println(sentData)
        val formBody: RequestBody = sentData.toRequestBody(JSON)

        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", "Bearer $TOKEN")
            .put(formBody)
            .build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful){
            throw IOException("Unexpected code $response")
        }
    }
    /*
     * method to refresh value from GW
     */
    fun refreshValue(){
        if (!this.wtioThings.wtioGw.isAvailable(HOST)){
            println("Gateway unavailable")
            return
        }
        val data = wtioThings.wtioGw.asyncCall(::getData) as JSONObject
        val newValue = data.get(data.names()?.get(0) as String)
        if (value!! != newValue){
            value = newValue
        }
    }

    /*
     * method tu update value in gw
     *
     * value - new value
     */
    fun updateValue(value: Any?) {
        if (!this.wtioThings.wtioGw.isAvailable(HOST)){
            println("Gateway unavailable")
            return
        }
        if (readonly){
            println("Can'T update readonly property")
            return
        }
        val url = "${wtioThings.BASE_URL}/properties/${name}"
        wtioThings.wtioGw.asyncCall{ pushData(url, name, value!!) }

    }
}