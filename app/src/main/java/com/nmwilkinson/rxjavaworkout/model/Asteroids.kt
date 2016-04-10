package com.nmwilkinson.rxjavaworkout.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by neil on 10/04/16.
 */
data class Asteroids(@Expose @SerializedName("element_count") val elementCount: Int,
                     @Expose @SerializedName("near_earth_objects") val nearEarthObjects: Map<String, List<Asteroid>>) {
    fun dates(): Set<String> {
        return nearEarthObjects.keys;
    }

    fun nearEarthObjects(date: String): List<Asteroid>? {
        return nearEarthObjects.get(date);
    }
}


