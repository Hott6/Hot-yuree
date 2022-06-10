package com.example.a220402.util

import android.content.Context
import android.content.SharedPreferences

object LoginSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean) {
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout():Boolean{
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
        return preferences.getBoolean(AUTO_LOGIN, false)
    }
}
