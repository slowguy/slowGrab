package com.bnb.grab.widget

import android.content.Context
import android.support.annotation.IntDef
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bnb.grab.R

/**
 * Created by wsl on 2018/1/4.
 */
open class CustomHeaderLayout : LinearLayout {

    var mContext: Context? = null
    var attrs: AttributeSet? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, def: Int) : super(context, attributeSet, def) {
        mContext = context
        attrs = attributeSet
        init()
    }

    private var back: TextView? = null
    private var desc: TextView? = null

    private fun init() {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_custom_header, this, true)
        initView(view)
        val ta = mContext!!.obtainStyledAttributes(attrs, R.styleable.CustomHeaderLayout)
        if (ta.hasValue(R.styleable.CustomHeaderLayout_h_bg_color)) {
            var bgC = ta.getColor(R.styleable.CustomHeaderLayout_h_bg_color, R.color.white)
            view.setBackgroundColor(bgC)
        }
        if (ta.hasValue(R.styleable.CustomHeaderLayout_h_tx_color)) {
            var txC = ta.getColor(R.styleable.CustomHeaderLayout_h_tx_color, R.color.color_333)
            back!!.setTextColor(txC)
            desc!!.setTextColor(txC)
        }
        if (ta.hasValue(R.styleable.CustomHeaderLayout_h_desc_text)) {
            var txDesc = ta.getText(R.styleable.CustomHeaderLayout_h_desc_text)
            desc!!.text = txDesc
        }
        if (ta.hasValue(R.styleable.CustomHeaderLayout_h_back_show)) {
            var backShow = ta.getBoolean(R.styleable.CustomHeaderLayout_h_back_show, false)
            if (backShow)
                back!!.visibility = View.VISIBLE
            else
                back!!.visibility = View.INVISIBLE
        }

    }

    private fun initView(view: View?) {
        back = view!!.findViewById(R.id.header_back) as TextView
        desc = view!!.findViewById(R.id.header_desc) as TextView
    }
}