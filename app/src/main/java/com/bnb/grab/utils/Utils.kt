package com.bnb.grab.utils

/**
 * Created by wsl on 2018/1/5.
 */
object Utils {

    private val regular: Array<String> = arrayOf("www", "http", "https")

    fun checkAnalyzeUrl(url: String): Boolean {
        var isMatching: Boolean = false
        for (item in 0 until regular.size) {
            if (url.startsWith(regular[item])) {
                isMatching = true
                break
            }
        }
        return false
    }
}