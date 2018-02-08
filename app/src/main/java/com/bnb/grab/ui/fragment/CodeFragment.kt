package com.bnb.grab.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bnb.grab.R
import com.bnb.grab.common.BaseFragment
import com.bnb.grab.widget.CustomProgress
import kotlinx.android.synthetic.main.fragment_code.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by wsl on 2018/1/4.
 */
class CodeFragment : BaseFragment(), CustomProgress.OnProgressScrollListener, View.OnScrollChangeListener, View.OnClickListener {

    private var codeStr: String? = ""
    private var textHeight: Int = 0

    private var isSnap: Boolean = false

    companion object {
        fun getInstance(code: String?): CodeFragment {
            val vFragment = CodeFragment()
            val bundle = Bundle()
            bundle.putString("code", code)
            vFragment.arguments = bundle
            return vFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (!bundle.isEmpty)
            codeStr = bundle.getString("code")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_code, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initEvent()
    }

    override fun initView(view: View?) {
        super.initView(view)
    }

    override fun initData() {
        super.initData()
        if (TextUtils.isEmpty(codeStr))
            codeBlank.visibility = View.VISIBLE
        else {
            codeBlank.visibility = View.GONE
            code!!.text = codeStr
        }
    }

    override fun initEvent() {
        super.initEvent()
        cusProgress.setOnProgressScrollListener(this)
        scroll.setOnScrollChangeListener(this)
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

    fun analyzeUrl(url: String?) {
        spinKit.visibility = View.VISIBLE
        MyThread(url).start()
    }

    internal inner class MyThread(private var url: String?) : Thread(url) {

        override fun run() {
            super.run()
            var doc: Document? = null
            try {
                doc = Jsoup.connect(url).get()
            } catch (e: Exception) {
                Log.e("wsl_log", "Exception -> $e")
            }
            analyzeDone(doc.toString())
        }
    }

    fun analyzeDone(doc: String?) {
        activity.runOnUiThread({
            spinKit.visibility = View.GONE
            codeBlank.visibility = View.GONE
            code!!.text = doc
            object : Thread() {
                override fun run() {
                    super.run()
                    Thread.sleep(500)
                    textHeight = code.height
                    cusProgress.setScrollHeight(textHeight)
//                    activity.runOnUiThread({
//                    })
                }
            }.start()
            scroll.smoothScrollTo(0, 0)
        })
    }

    fun scrollToTop() {
        scroll.smoothScrollTo(0, 0)
    }

    override fun pScroll(ratio: Float) {
        scroll.scrollTo(0, (textHeight * ratio).toInt())
    }

    override fun pStatus(b: Boolean) {
        isSnap = b
    }

    override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        var r = (scrollY / (textHeight - scroll.height).toFloat())
        if (r > 1f) r = 1f
        if (r < 0f) r = 0f
        if (!isSnap)
            cusProgress.moveProgress(r)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

        }
    }
}