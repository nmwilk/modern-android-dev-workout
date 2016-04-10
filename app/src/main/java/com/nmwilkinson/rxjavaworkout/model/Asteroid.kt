package com.nmwilkinson.rxjavaworkout.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by neil on 09/04/16.
 */
data class Asteroid(@Expose val name: String,
                    @Expose @SerializedName("absolute_magnitude_h") val absoluteMagnitudeH: Float,
                    @Expose @SerializedName("close_approach_data") val closeApproachData: List<CloseApproach>,
                    @Expose @SerializedName("neo_reference_id") val neoRefId: Int)

data class CloseApproach(@Expose @SerializedName("miss_distance") val missDistance: MissDistance)

data class MissDistance(@Expose val kilometers: Double, @Expose @SerializedName("close_approach_date") val date: String)
