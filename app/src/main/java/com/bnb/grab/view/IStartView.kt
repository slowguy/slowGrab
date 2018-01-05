package com.bnb.grab.view

import org.jsoup.nodes.Document

/**
 * Created by wsl on 2018/1/4.
 */
interface IStartView {

    fun getUrl(): String

    fun setUrl(url: String?)

    fun showLoading(show: Boolean)

    fun analyzeDone(doc: Document,url: String?)

}