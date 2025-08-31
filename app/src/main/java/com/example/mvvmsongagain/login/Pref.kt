package com.example.mvvmsongagain.login

import android.content.Context
import android.content.SharedPreferences

object Pref {

    private lateinit var pref: SharedPreferences

    fun init(context: Context) {
        pref = context.getSharedPreferences("rem_me", Context.MODE_PRIVATE)
    }

    fun setRememberMe(value: Boolean) {
        pref.edit().putBoolean("rem_me", value).apply()
    }

    fun getRememberMe(): Boolean {
        return pref.getBoolean("rem_me", false)
    }


}