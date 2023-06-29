package com.example.radiusagent.api

import com.example.radiusagent.models.Facilities
import retrofit2.http.GET

interface SimpleApi {

    @GET("iranjith4/ad-assignment/db")
    suspend fun getFacilities():Facilities
}