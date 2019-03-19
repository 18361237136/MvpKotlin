package com.example.mvpkotlin.ui.fragment

import android.os.Bundle
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseFragment

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 上午11:32
 * Describe:我的
 */
class MineFragment : BaseFragment(){
    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
    }

    private var mTitle:String?=null

    companion object {
        fun getInstance(title:String):MineFragment{
            val fragment=MineFragment()
            val bundle= Bundle()
            fragment.arguments=bundle
            fragment.mTitle=title
            return fragment
        }
    }
}