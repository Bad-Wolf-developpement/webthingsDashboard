package io.webt.webthingsdashboard

import android.content.Context
import android.os.Parcelable
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.json.JSONArray
import java.net.InetAddress

/*
 *Webthings.io gateway object
 *
 * host -- String: url or ip of the webthings gateway
 * token -- String: web token genated in the gatway to avoid login with username and password
 */

@Parcelize
class WebtioGateway(internal val HOST: String,
                    internal val TOKEN: String,
                    val PORT: String,
                    val SSL: Boolean = false,
                    val context : @RawValue Context) : Parcelable {
    internal val client = OkHttpClient()
    internal var BASE_URL: String = if (SSL){
        "https://$HOST:$PORT/things"
    }else{
        "http://$HOST:$PORT/things"
    }
    var gwThings: MutableMap<String, WebtioThings> = mutableMapOf()//list of things object

    /*
     *Get list of Things from the Gateway,
     *
     * return -- map of string, id : name
     */
    private fun getThings(): MutableMap<String, String> {
        val things = mutableMapOf<String, String>()
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
        (0 until JSONArray(data).length()).forEach {
            val json = JSONArray(data).getJSONObject(it)
            val id = json.get("href").toString().removePrefix("/things/")
            val name = json.get("title").toString()
            things.put(id, name)
        }
        return things
    }

    /*
     *Make an async call to a function(used for network call)
     *
     * func -- ::function to call
     *
     * return -- function return
     */

    internal fun asyncCall(func: () -> Any): Any{
        val result = runBlocking {
            withContext(Dispatchers.Default) { func() }
        }
        return result
    }

    /*
     * Method to initialize the thing and associate it to the gateway
     * fill a list of gwThings
     */
    fun initializeThings(){
        if (!this.isAvailable(this.HOST)){
            Toast.makeText(
                context,
                R.string.invalidPort,
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val things = asyncCall(::getThings) as MutableMap<String, String>

        for (thing in things) {
            gwThings.put(thing.key as String, WebtioThings(thing.key as String, thing.value as String, this))
        }
        //initialize properties of the things
        for (thing in gwThings){
            thing.value.initProperties()
        }
    }

    /*
     *Method to test if gateway is reachable before trying to send request
     *
     * host -- gateway ip or url
     *
     * return Boolean
     */
    internal fun isAvailable(host: String): Boolean {
        return asyncCall { InetAddress.getByName(host).isReachable(30) } as Boolean
    }
}