package io.webt.webthingsdashboard

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import org.json.JSONArray
import java.net.Inet6Address
import java.net.InetAddress


/*
 *Webthings.io gateway object
 *
 * host -- String: url or ip of the webthings gateway
 * token -- String: web token genated in the gatway to avoid login with username and password
 */
class WebtioGateway(internal val HOST: String,
                   internal val TOKEN: String,
                   private val PORT: String,
                    private val SSL: Boolean = false) {

    internal val client = OkHttpClient()
    internal var BASE_URL: String
    var gwThings: MutableMap<String, WebtioThings> = mutableMapOf()//list of things object

    init {
        if (SSL){
            BASE_URL = "https://$HOST:$PORT/things"
        }else{
            BASE_URL = "http://$HOST:$PORT/things"
        }
    }
    /*
     *Get list of Things from the Gateway,
     *
     * return -- map of string, id : name
     */
    fun getThings(): MutableMap<String, String> {
        var things = mutableMapOf<String, String>()
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
        (0 until JSONArray(data).length()).forEach {
            var json = JSONArray(data).getJSONObject(it)
            var id = json.get("href").toString().removePrefix("/things/")
            var name = json.get("title").toString()
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
        var result = runBlocking {
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
            println("Gateway Unavailable")
            return
        }
        var things = asyncCall(::getThings) as MutableMap<String, String>

        for (thing in things) {
            gwThings.put(thing.key as String, WebtioThings(thing.key as String, thing.value as String, this))
            //todo change the type to avoid double .key call
        }
    }
    /*
     *Method to test if gateway is reachable before trying to send request
     *
     * host -- gateway ip or url
     *
     * return Boolean
     */
    internal fun isAvailable(host: String): Boolean{
        val result = asyncCall({ InetAddress.getByName(host).isReachable(30) }) as Boolean
        return result
    }
}