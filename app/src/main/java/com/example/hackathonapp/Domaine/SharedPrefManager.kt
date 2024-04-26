package com.example.hackathonapp.Domaine

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val used_key = "USED"
    }

    var used: String?
        get() = sharedPreferences.getString(used_key, null)
        set(value) {
            sharedPreferences.edit().putString(used_key, value).apply()
        }
}
