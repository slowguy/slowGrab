package com.bnb.grab.utils

import android.content.Context
import android.text.TextUtils
import android.util.Log

/**
 * Created by wsl on 2018/1/5.
 */
object Utils {

    private val regular: Array<String> = arrayOf("http", "https")
    private val r1 = Regex("^([h][t]{2}[p]://|[h][t]{2}[p][s]://)(([a-z0-9-~]+).)+([^.]+)$")

    fun checkAnalyzeUrl(context: Context, url: String): Boolean {
        val lowerCase = url.toLowerCase()
        if (TextUtils.isEmpty(url)) {
            MyToast.showSortToast(context, "url CAN'T BE EMPTY")
            return false
        }
        if (!lowerCase.startsWith(regular[0]) && !lowerCase.startsWith(regular[1])) {
            MyToast.showSortToast(context, "url MUST starts with http or https!")
            return false
        }
        if (!url.matches(r1)) {
            MyToast.showSortToast(context, "url NOT CORRECT!")
            return false
        }
        return true
    }
}