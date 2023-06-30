package com.example.radiusagent.models



data class Facilities(
    val exclusions: List<List<Exclusion>>,
    val facilities: List<Facility>
)