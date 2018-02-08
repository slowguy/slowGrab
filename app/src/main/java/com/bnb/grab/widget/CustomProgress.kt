package com.bnb.grab.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

/**
* Created by wsl on 2018/2/1.
*/
class CustomProgress : LinearLayout, View.OnTouchListener {

    private var mContext: Context? = null
    private var width: Int? = 0
    private var height: Int? = 0
    private var cube: ImageView? = null

    private var downY: Int = 0
    private var cubeWidth: Int = 0
    private var cubeHeight: Int = 0
    private var cubeTop: Int = 0
    private var maxTop: Int = 0

    private var sHeight: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, def: Int) : super(context, attributeSet, def) {
        mContext = context
        init()
    }

    private fun init() {
        orientation = LinearLayout.VERTICAL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        cube = getChildAt(0) as ImageView
        cube!!.setOnTouchListener(this)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        height = getHeight()
        width = getWidth()
        cubeHeight = cube!!.height
        cubeWidth = cube!!.width
        maxTop = height!! - cubeHeight
    }

    fun setScrollHeight(sHeight: Int) {
        (mContext as Activity).runOnUiThread({
            this.sHeight = sHeight
            visibility = if (sHeight <= height!!)
                View.GONE
            else
                View.VISIBLE
        })
    }

    fun moveProgress(r: Float) {
        val t = (maxTop * r).toInt()
        cubeTop = t
        cube!!.layout(0, t, cubeWidth, t + cubeHeight)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val moveY: Int
        val devY: Int
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                downY = event.rawY.toInt()
                listener?.pStatus(true)
            }
            MotionEvent.ACTION_MOVE -> {
                moveY = event.rawY.toInt()
                devY = moveY - this.downY
                cubeTop += devY
                if (cubeTop <= 0) cubeTop = 0
                if (cubeTop >= maxTop) cubeTop = maxTop
                cube!!.layout(0, cubeTop, cubeWidth, cubeTop + cubeHeight)
                downY = moveY
                listener?.pScroll(cubeTop.toFloat() / maxTop.toFloat())
            }
            MotionEvent.ACTION_UP -> {
                listener?.pStatus(false)
            }
        }
        return true
    }

    interface OnProgressScrollListener {
        fun pScroll(ratio: Float)

        fun pStatus(b: Boolean)
    }

    private var listener: OnProgressScrollListener? = null

    fun setOnProgressScrollListener(listener: OnProgressScrollListener) {
        this.listener = listener
    }

}