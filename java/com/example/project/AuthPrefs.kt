package com.example.project

import android.content.Context

object AuthPrefs {
    private const val PREFS = "auth_prefs"
    private const val KEY_EMAIL = "email"
    private const val KEY_PASSWORD = "password"

    fun saveUser(context: Context, email: String, password: String) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun getUser(context: Context): Pair<String?, String?> {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getString(KEY_EMAIL, null) to prefs.getString(KEY_PASSWORD, null)
    }
}