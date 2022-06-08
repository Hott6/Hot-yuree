package com.example.a220402.util

import android.content.Context
import android.content.SharedPreferences
import com.example.a220402.activity.SettingActivity

object LoginSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private lateinit var preferences: SharedPreferences

    fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(context: Context): Boolean {
        return getSharedPreference(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean) {
        getSharedPreference(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    fun setLogout(context: SettingActivity):Boolean{
        getSharedPreference(context).edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
        return getSharedPreference(context).getBoolean(AUTO_LOGIN, false)
    }
}
