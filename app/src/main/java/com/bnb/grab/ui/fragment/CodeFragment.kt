package com.bnb.grab.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bnb.grab.R
import com.bnb.grab.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_code.*

/**
 * Created by wsl on 2018/1/4.
 */
class CodeFragment : BaseFragment() {

    private var codeStr: String? = ""

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
    }

    override fun initView(view: View?) {
        super.initView(view)
    }

    override fun initData() {
        super.initData()
        code!!.setText(codeStr)
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
}