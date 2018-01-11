package com.bnb.grab.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by wsl on 2018/1/8.
 */
object SpUtils {

    val spTag: String = "GRAB_DATA"

    val HISTORY_KEY: String = "HISTORY"

    fun saveStr(context: Context, key: String?, str: String?) {
        context.getSharedPreferences(spTag, 0).edit().putString(key, str).apply()
    }

    fun getStr(context: Context, key: String?): String {
        return context.getSharedPreferences(spTag, 0).getString(key, "")
    }
}