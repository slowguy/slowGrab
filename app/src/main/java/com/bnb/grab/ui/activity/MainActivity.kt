package com.bnb.grab.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.widget.FrameLayout

import butterknife.ButterKnife
import com.bnb.grab.R
import com.bnb.grab.common.BaseActivity
import com.bnb.grab.ui.fragment.StartFragment

class MainActivity : BaseActivity() {

    val container: FrameLayout by lazy { findViewById(R.id.frag_container) as FrameLayout }
    var fragList: ArrayList<Fragment>? = null
    var startFrag: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initView()
    }

    override fun initView() {
        setupFragment()
    }

    fun setupFragment() {
//        fragList = arrayListOf()
        val fragmentTrans = getFragmentTrans()
        startFrag = StartFragment()
        fragmentTrans.add(R.id.frag_container, startFrag)
        fragmentTrans.show(startFrag)
        fragmentTrans.commit()
    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    fun getFragmentTrans(): FragmentTransaction {
        return supportFragmentManager.beginTransaction()
    }
}
