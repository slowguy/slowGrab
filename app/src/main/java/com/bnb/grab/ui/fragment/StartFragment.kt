package com.bnb.grab.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.annotation.UiThread
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.bnb.grab.R
import com.bnb.grab.common.BaseFragment
import com.bnb.grab.presenter.IStartPresenter
import com.bnb.grab.presenter.impl.StartPresenter
import com.bnb.grab.ui.activity.DocDetailActivity
import com.bnb.grab.utils.MyToast
import com.bnb.grab.view.IStartView
import kotlinx.android.synthetic.main.fragment_start.*
import org.jsoup.nodes.Document

/**
 * Created by wsl on 2018/1/3.
 */
open class StartFragment : BaseFragment(), View.OnClickListener, IStartView {

    var startPre: IStartPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_start, container, false)
        initView(view)
        startPre = StartPresenter(this)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }

    override fun initView(view: View?) {

    }

    override fun initData() {
        super.initData()
    }

    override fun initEvent() {
        analyze.setOnClickListener(this)
        preview.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.analyze -> {
                val url = editUrl.text.toString()
                if (TextUtils.isDigitsOnly(url)) {
                    MyToast.showSortToast(activity, "URL CAN'T BE EMPTY")
                    return
                }
                startPre!!.startAnalyze(url)
                MyToast.showSortToast(activity, "grab")
            }
            R.id.preview -> {
                MyToast.showSortToast(activity, "preview")
            }
        }
    }

    override fun setUrl(url: String?) {
        editUrl!!.setText(url)
    }

    override fun getUrl(): String {
        return editUrl!!.text.toString()
    }

    override fun showLoading(show: Boolean) {
        when (show) {
            true -> {
                spinKit.visibility = View.VISIBLE
            }
            false -> {
                spinKit.visibility = View.INVISIBLE
            }
        }
    }

    override fun analyzeDone(doc: Document,url:String?) {
        activity.runOnUiThread {
            showLoading(false)
            val intent = Intent(activity, DocDetailActivity::class.java)
            intent.putExtra("detail_type", "a")
            intent.putExtra("url", url)
            intent.putExtra("code", doc.toString())
            startActivity(Intent(intent))
        }

    }

}