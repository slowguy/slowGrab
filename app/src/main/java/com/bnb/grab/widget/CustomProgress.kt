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
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        cube = getChildAt(0) as ImageView?
        cube!!.setOnTouchListener(this)
        Log.e("view_log", "onMeasure")
    }

//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        Log.e("view_log", "onLayout")
//        width = getWidth()
//        height = getHeight()
//    }

//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        Log.e("view_log", "onLayout")
//        width = getWidth()
//        height = getHeight()
//    }

//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//
//    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("cube_log", "action_down")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e("cube_log", "action_move")
            }
            MotionEvent.ACTION_UP -> {
                Log.e("cube_log", "action_up")
            }
        }
        return true
    }

}