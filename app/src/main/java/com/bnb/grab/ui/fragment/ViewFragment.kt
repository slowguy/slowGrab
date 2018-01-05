package com.bnb.grab.ui.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bnb.grab.R
import com.bnb.grab.common.BaseFragment

import kotlinx.android.synthetic.main.fragment_view.*

class ViewFragment : BaseFragment() {

    private var url: String? = ""

    companion object {
        fun getInstance(url: String?): ViewFragment {
            val vFragment = ViewFragment()
            val bundle = Bundle()
            bundle.putString("url", url)
            vFragment.arguments = bundle
            return vFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (!bundle.isEmpty)
            url = bundle.getString("url")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_view, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    override fun initView(view: View?) {
        super.initView(view)
    }

    override fun initData() {
        super.initData()
        webConfig(web)
        web.loadUrl(url)
    }

    private fun webConfig(web: WebView?) {
        val settings = web!!.settings
        settings.javaScriptEnabled = true
        settings.defaultTextEncodingName = "utf-8"
        settings.blockNetworkImage = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        web.setWebViewClient(MyWebViewClient())
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }
}