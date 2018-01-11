package com.bnb.grab.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bnb.grab.R

/**
 * Created by wsl on 2018/1/8.
 */
class CustomHistoryLayout : LinearLayout, View.OnClickListener {

    var mContext: Context? = null
    var attrs: AttributeSet? = null

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, def: Int) : super(context, attributeSet, def) {
        mContext = context
        attrs = attributeSet
        init()
    }

    var historyText: TextView? = null
    var historyDel: ImageView? = null

    private fun init() {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_history, this, true)
        initView(view)
        initEvent()
    }

    private fun initView(view: View?) {
        historyText = view!!.findViewById(R.id.historyText) as TextView
        historyDel = view!!.findViewById(R.id.historyDel) as ImageView
    }

    private fun initEvent() {
        historyText!!.setOnClickListener(this)
        historyDel!!.setOnClickListener(this)
    }

    open fun setUrl(url: String) {
        historyText!!.text = url
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.historyText -> {
                listener!!.textClick((historyText!!.text).toString())
            }
//            R.id.historyDel -> {
//                listener!!.delClick()
//            }
        }
    }

    fun setOnHistoryActionListener(listener: OnHistoryActionListener) {
        this.listener = listener
    }

    var listener: OnHistoryActionListener? = null

    interface OnHistoryActionListener {
        fun textClick(text: String)
//        fun delClick()
    }
}