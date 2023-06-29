package com.example.radiusagent.repository

import com.example.radiusagent.api.RetroFitInstance
import com.example.radiusagent.models.Facilities

class Repository {
    suspend fun getFacilities():Facilities{
        return RetroFitInstance.api.getFacilities()
    }
}