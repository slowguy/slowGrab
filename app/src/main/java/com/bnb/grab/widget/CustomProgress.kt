package com.bnb.grab.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bnb.grab.R

/**
 * Created by wsl on 2018/2/1.
 */
class CustomProgress : LinearLayout {

    var mContext: Context? = null
    var width: Int? = 0
    var height: Int? = 0
//    var paint1: Paint? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, def: Int) : super(context, attributeSet, def) {
        mContext = context
        init()
    }

    private fun init() {
        orientation = LinearLayout.VERTICAL
        setBackgroundColor(Color.parseColor("#66ff0000"))
        Log.e("view_log", "init")
//        paint1 = Paint()
//        paint1!!.color = mContext!!.resources.getColor(R.color.color_44a9cc)

    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        Log.e("view_log", "onMeasure")
//    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.e("view_log", "onLayout")
        width = getWidth()
        height = getHeight()
    }

//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        Log.e("view_log", "onLayout")
//        width = getWidth()
//        height = getHeight()
//    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//
//    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return super.onTouchEvent(event)
    }

}