package com.infrasoft.infraoffer

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ShareHelper {

    /**
     * Save and get ArrayList in SharedPreference
     */

    fun saveArrayList(list: ArrayList<String>, key: String, context: Context) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()     // This line is IMPORTANT !!!
    }

    fun getArrayList(key: String, context: Context): ArrayList<String> {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = prefs.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type
        return gson.fromJson(json, type)
    }
}