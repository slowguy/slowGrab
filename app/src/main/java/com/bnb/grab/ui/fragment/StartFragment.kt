package com.bnb.grab.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bnb.grab.R
import com.bnb.grab.`interface`.CustomTextWatcher
import com.bnb.grab.common.BaseFragment
import com.bnb.grab.presenter.IStartPresenter
import com.bnb.grab.presenter.impl.StartPresenter
import com.bnb.grab.ui.activity.DocDetailActivity
import com.bnb.grab.utils.MyToast
import com.bnb.grab.utils.SpUtils
import com.bnb.grab.utils.Utils
import com.bnb.grab.view.IStartView
import com.bnb.grab.widget.CustomHistoryLayout
import kotlinx.android.synthetic.main.fragment_start.*
import org.jsoup.nodes.Document

/**
 * Created by wsl on 2018/1/3.
 */
open class StartFragment : BaseFragment(), View.OnClickListener, IStartView, CustomHistoryLayout.OnHistoryActionListener {

    var startPre: IStartPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_start, container, false)
        startPre = StartPresenter(this)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initEvent()
    }

    override fun initView(view: View?) {
    }

    override fun initData() {
        super.initData()
        setHistoryLayout()
    }

    override fun initEvent() {
        analyze.setOnClickListener(this)
        preview.setOnClickListener(this)
        clear.setOnClickListener(this)
        urlClear.setOnClickListener(this)
        editUrl.addTextChangedListener(watcher)
    }

    private val watcher: CustomTextWatcher = object : CustomTextWatcher() {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            super.onTextChanged(s, start, before, count)
            if (TextUtils.isEmpty(s))
                urlClear.visibility = View.INVISIBLE
            else
                urlClear.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.preview -> {
                val url = editUrl.text.toString()
                if (Utils.checkAnalyzeUrl(activity, url)) {//right url
                    actionClick("a", url)
                }

            }
            R.id.analyze -> {
                val url = editUrl.text.toString()
                if (Utils.checkAnalyzeUrl(activity, url)) {//right url
                    actionClick("b", url)
                }
            }
            R.id.clear -> {
                SpUtils.saveStr(activity, SpUtils.HISTORY_KEY, "")
                MyToast.showSortToast(activity, "done")
                setHistoryLayout()
            }
            R.id.urlClear -> {
                editUrl.setText("")
            }
        }
    }

    private fun actionClick(tag: String, url: String) {
        editUrl.setText("")
        //TODO save history & skip to inner activity
        startPre!!.saveHistory(url)
        startPre!!.startAnalyze(tag, url)
        setHistoryLayout()
    }

    override fun setUrl(url: String?) {
        editUrl!!.setText(url)
    }

    override fun getUrl(): String {
        return editUrl!!.text.toString()
    }

    override fun showLoading(show: Boolean) {
        if (show) {
            spinKit.visibility = View.VISIBLE
        } else {
            spinKit.visibility = View.INVISIBLE
        }
    }

    override fun analyzeDone(tag: String, doc: Document?, url: String?) {
        activity.runOnUiThread {
            showLoading(false)
            val intent = Intent(activity, DocDetailActivity::class.java)
            intent.putExtra("detail_type", tag)
            intent.putExtra("url", url)
            if (doc != null)
                intent.putExtra("code", doc.toString())
            startActivity(Intent(intent))
        }
    }

    override fun setHistoryLayout() {
        Log.e("wqeqwe", "setHistoryLayout")
        val arr = startPre!!.getHistory()
        if ("" == arr[0]) {
            historyLayout.visibility = View.GONE
            return
        }
        if (historyLayout.childCount > 1) {
            for (n in historyLayout.childCount - 1 downTo 1) {
//            for (n in 1 downTo 0) {
                Log.e("wqeqwe", "count - ${historyLayout.childCount}, n - $n")
                historyLayout.removeViewAt(n)
            }
        }
        for (i in arr.size - 1 downTo arr.size - 5) {
            if (i < 0) return
            historyLayout.visibility = View.VISIBLE
            val historyItem = arr[i]
            var ch = CustomHistoryLayout(activity)
            ch.setOnHistoryActionListener(this)
            ch.setUrl(historyItem)
            historyLayout.addView(ch)
        }
    }

    override fun textClick(text: String) {
        editUrl.setText(text)
    }

}