package com.example.mvpkotlin.ui.fragment

import android.os.Bundle
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseFragment

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 上午11:31
 * Describe:热门
 */
class HotFragment : BaseFragment(){
    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
    }

    private var mTitle:String?=null

    companion object {
        fun getInstance(title:String):HotFragment{
            val fragment=HotFragment()
            val bundle= Bundle()
            fragment.arguments=bundle
            fragment.mTitle=title
            return fragment
        }
    }
}