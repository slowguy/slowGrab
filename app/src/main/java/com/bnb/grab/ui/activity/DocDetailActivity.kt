package com.bnb.grab.ui.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TextView

import com.bnb.grab.R
import com.bnb.grab.common.BaseActivity
import com.bnb.grab.ui.fragment.CodeFragment
import com.bnb.grab.ui.fragment.ViewFragment
import kotlinx.android.synthetic.main.activity_doc_detail.*

class DocDetailActivity : BaseActivity(), View.OnClickListener {

    var viewFragment: ViewFragment? = null
    var codeFragment: CodeFragment? = null
    var dType: String? = "a"
    var url: String? = ""
    var code: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_detail)
        initView()
        initEvent()
    }

    override fun initView() {
        super.initView()
        dType = intent.getStringExtra("detail_type")
        url = intent.getStringExtra("url")
        code = intent.getStringExtra("code")
        setupFragment()
    }

    override fun initEvent() {
        super.initEvent()
        viewText.setOnClickListener(this)
        codeText.setOnClickListener(this)
        detail_header.setOnClickListener(this)
    }

    private fun setupFragment() {
        viewFragment = ViewFragment.getInstance(url)
        codeFragment = CodeFragment.getInstance(code)
        val trans = getFragmentTrans()
        trans.add(R.id.frag_container, viewFragment)
        trans.add(R.id.frag_container, codeFragment)
        trans.commit()
        selectFrag(dType)
    }

    private fun getFragmentTrans(): FragmentTransaction {
        return supportFragmentManager.beginTransaction()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.viewText -> {
                dType = "a"
                selectFrag(dType)
            }
            R.id.codeText -> {
                dType = "b"
                selectFrag(dType)
            }
            R.id.detail_header -> {
                if (dType == "b") {
                    codeFragment!!.scrollToTop()
                }
            }
        }
    }

    private fun selectFrag(tag: String?) {
        val trans = getFragmentTrans()
        when (tag) {
            "a" -> {
                viewText.isSelected = true
                codeText.isSelected = false
                trans.show(viewFragment)
                trans.hide(codeFragment)
                trans.commit()
            }
            "b" -> {
                viewText.isSelected = false
                codeText.isSelected = true
                trans.show(codeFragment)
                trans.hide(viewFragment)
                trans.commit()
            }
        }
    }

    open fun skipFragmentByTag(tag: String?, currentUrl: String?) {
        val fragmentTrans = getFragmentTrans()
        dType = tag
        when (tag) {
            "a" -> {
                viewText.isSelected = true
                codeText.isSelected = false
                fragmentTrans.hide(codeFragment)
                fragmentTrans.show(viewFragment)
                fragmentTrans.commit()
            }
            "b" -> {
                viewText.isSelected = false
                codeText.isSelected = true
                fragmentTrans.hide(viewFragment)
                fragmentTrans.show(codeFragment)
                fragmentTrans.commit()
                codeFragment!!.analyzeUrl(currentUrl)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN && dType == "a") {
            handleBackClick()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    private fun handleBackClick() {
        if (viewFragment!!.judgeWeb()) {
            finish()
        }
    }
}
