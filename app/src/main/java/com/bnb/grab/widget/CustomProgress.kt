package com.bnb.grab.widget

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bnb.grab.R

/**
 * Created by wsl on 2018/2/1.
 */
class CustomProgress : LinearLayout, View.OnTouchListener {

    var mContext: Context? = null
    var width: Int? = 0
    var height: Int? = 0
    var cube: ImageView? = null

    var downY: Int = 0
    var cubeWidth: Int = 0
    var cubeHeight: Int = 0
    var cubeTop: Int = 0
    var maxTop: Int = 0

    var sHeight: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, def: Int) : super(context, attributeSet, def) {
        mContext = context
        init()
    }

    private fun init() {
        orientation = LinearLayout.VERTICAL
        setBackgroundColor(Color.parseColor("#22ff0000"))

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        cube = getChildAt(0) as ImageView
        cube!!.setOnTouchListener(this)
        Log.e("view_log", "onMeasure")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        height = getHeight()
        width = getWidth()
        cubeHeight = cube!!.height
        cubeWidth = cube!!.width
        maxTop = height!! - cubeHeight
    }

    open fun setScrollHeight(sHeight: Int) {
        Log.e("slslsl", "sHeight-$sHeight")
        (mContext as Activity).runOnUiThread({
            this.sHeight = sHeight
            if (sHeight <= height!!) {
                visibility = View.GONE
            }
        })
    }

    fun moveProgress(r: Float) {
        var t = (maxTop * r).toInt()
        Log.e("xxxx_log", "cubeTop->$t")
        cubeTop = t
        cube!!.layout(0, t, cubeWidth, t + cubeHeight)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        var moveY = 0
        var devY = 0
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
//                Log.e("cube_log", "l - 0,t - $cubeTop,r - $cubeWidth,b - ${cubeTop + cubeHeight}")
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