package com.cursosant.inventorybase.common

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


class InventoryPreferences(application: Application) {
    private val preferences: SharedPreferences

    companion object{
        private const val K_WELCOME = "is_welcome"

        private var INSTANCE: InventoryPreferences? = null

        fun getInstance(application: Application) = INSTANCE ?: synchronized(this){
            InventoryPreferences(application).also { INSTANCE = it }
        }
    }

    init {
        preferences = PreferenceManager.getDefaultSharedPreferences(application)
    }

    fun setWelcome(value: Boolean){
        preferences.edit().putBoolean(K_WELCOME, value).apply()
    }

    fun getWelcome(): Boolean = preferences.getBoolean(K_WELCOME, true)
}