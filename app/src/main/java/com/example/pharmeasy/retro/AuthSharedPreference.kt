package com.example.pharmeasy.retro

import android.content.Context
import android.content.SharedPreferences
import com.example.pharmeasy.ScheduleApplication

class AuthSharedPreference {

    private val AUTH_PREFERENCE = "AuthTokenPreferences"
    private val AUTHTOKEN = "authtoken"
    private val PASS = "psw"
    private val USER = "uname"
    private var authSharedPreference: SharedPreferences =
        ScheduleApplication.getApplicationContex().getSharedPreferences(AUTH_PREFERENCE, Context.MODE_PRIVATE)

    fun addToken(token: String?) {
        val editor = authSharedPreference.edit()
        editor.putString(AUTHTOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        return authSharedPreference.getString(AUTHTOKEN, "").toString()
    }

    fun saveCredentials(userName: String?, pass: String?) {
        val editor = authSharedPreference.edit()
        editor.putString(USER, userName)
        editor.putString(PASS, pass)
        editor.apply()
    }

    fun getUsername(): String? {
        return authSharedPreference.getString(USER, null)
    }

    fun getPassword(): String? {
        return authSharedPreference.getString(PASS, null)

    }

    fun clear() {
        val editor = authSharedPreference.edit()
        editor.clear()
        editor.apply()
    }

}