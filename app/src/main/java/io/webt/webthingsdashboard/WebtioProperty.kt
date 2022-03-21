package io.webt.webthingsdashboard
import io.webt.webthingsdashboard.WebtioThings

/*
 *Object representing a things in the webthings.io gateway
 *
 * id -- id of the things
 * name -- name of the things(title)
 * wtioGw -- gateway who own this thing
 */

class WebtioProperty(val id: String, var name: String, val wtioThings: WebtioThings) {
    private val BASE_URL = "${wtioThings.BASE_URL}/properties/${id}"
    private val client = wtioThings.client
    private val TOKEN = wtioThings.TOKEN
}