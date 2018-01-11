package com.bnb.grab.presenter

/**
 * Created by wsl on 2018/1/4.
 */
interface IStartPresenter {

    fun startAnalyze(tag: String, url: String)

    fun saveHistory(url: String)

    fun getHistory(): List<String>

}