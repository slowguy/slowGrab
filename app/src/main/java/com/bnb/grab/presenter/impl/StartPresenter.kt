package com.bnb.grab.presenter.impl

import android.util.Log
import com.bnb.grab.presenter.IStartPresenter
import com.bnb.grab.ui.fragment.StartFragment
import com.bnb.grab.utils.SpUtils
import com.bnb.grab.view.IStartView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.ConnectException

/**
 * Created by wsl on 2018/1/4.
 */
open class StartPresenter : IStartPresenter {

    open var startView: IStartView? = null

    constructor(startView: IStartView?) {
        this.startView = startView
    }

    override fun startAnalyze(tag: String, url: String) {
        startView!!.showLoading(true)
        MyThread(tag, url, startView).start()
    }

    class MyThread(private var tag: String?, private var url: String?, private var startView: IStartView?) : Thread(url) {

        override fun run() {
            super.run()
            var doc: Document? = null
            try {
                doc = Jsoup.connect(url).get()
//                startView!!.analyzeDone(tag!!, doc!!, url)
            } catch (e: Exception) {
                Log.e("wsl_log", "Exception -> $e")
            }
            Log.e("wsl_log", "doc -> $doc")
            startView!!.analyzeDone(tag!!, doc, url)
        }
    }

    override fun saveHistory(url: String) {
        var saveUrl = url
        val str = SpUtils.getStr((startView as StartFragment).activity, SpUtils.HISTORY_KEY)
        val arr = str.split("^^")
        if (arr.contains(saveUrl))
            return
        if (!str.isEmpty())
            saveUrl = "^^" + url
        saveUrl = str + saveUrl
        SpUtils.saveStr((startView as StartFragment).activity, SpUtils.HISTORY_KEY, saveUrl)
    }

    override fun getHistory(): List<String> {
        val str = SpUtils.getStr((startView as StartFragment).activity, SpUtils.HISTORY_KEY)
        return str.split("^^")
    }

}