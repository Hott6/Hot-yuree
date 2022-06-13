package com.example.a220402.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

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
        preferences.edit(commit = true) { putBoolean("AUTO_LOGIN", value) } //Android KTX 사용
    }

    fun setLogout(): Boolean {
        preferences.edit(commit = true) {
            remove("AUTO_LOGIN")
            clear()
        }
        return preferences.getBoolean(AUTO_LOGIN, false)
    }
}
