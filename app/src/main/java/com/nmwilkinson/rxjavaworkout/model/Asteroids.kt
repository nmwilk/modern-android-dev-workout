package com.nmwilkinson.rxjavaworkout.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by neil on 10/04/16.
 */
data class Asteroids(@Expose @SerializedName("element_count") val elementCount: Int,
                     @Expose @SerializedName("near_earth_objects") val nearEarthObjects: Map<String, List<Asteroid>>) {
    companion object {}

    fun dates(): Set<String> {
        return nearEarthObjects.keys;
    }

    fun nearEarthObjects(date: String): List<Asteroid>? {
        return nearEarthObjects.get(date);
    }
}

fun distanceComparator(): Comparator<Asteroid> {
    return Comparator { lhs, rhs ->
        lhs.closeApproachData.get(0).missDistance.kilometers
                .compareTo(rhs.closeApproachData.get(0).missDistance.kilometers)
    }
}


