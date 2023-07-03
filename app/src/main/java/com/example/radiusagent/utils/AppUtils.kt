package com.example.radiusagent.utils

import android.content.Context
import android.content.SharedPreferences

object AppUtils {

    private lateinit var pref: SharedPreferences
    fun initializePreferences(context: Context) {
        pref =
            context.getSharedPreferences(Constants.RADIUS_AGENT_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun saveStringInPreferences(key: String, value: String) {
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getSavedStringFromPreferences(key: String): String? {
        return pref.getString(key, null)
    }


}