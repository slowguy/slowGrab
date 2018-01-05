package com.bnb.grab.presenter.impl

import com.bnb.grab.presenter.IStartPresenter
import com.bnb.grab.view.IStartView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by wsl on 2018/1/4.
 */
open class StartPresenter : IStartPresenter {

    open var startView: IStartView? = null

    constructor(startView: IStartView?) {
        this.startView = startView
    }

    override fun startAnalyze(url: String) {
        startView!!.showLoading(true)
        MyThread(url, startView).start()
    }

    class MyThread(private var url: String?, private var startView: IStartView?) : Thread(url) {

        override fun run() {
            super.run()
            val doc: Document? = Jsoup.connect(url).get()
            startView!!.analyzeDone(doc!!,url)
        }
    }
}