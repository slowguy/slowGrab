package com.bnb.grab

import android.os.Bundle
import android.widget.FrameLayout

import butterknife.ButterKnife
import com.bnb.grab.common.BaseActivity

class MainActivity : BaseActivity() {

    val container: FrameLayout by lazy { findViewById(R.id.frag_container) as FrameLayout }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setupFragment(){

    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
