package com.example.mvpkotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseFragment
import com.example.mvpkotlin.base.BaseFragmentAdapter
import com.example.mvpkotlin.utils.StatusBarUtil
import com.example.mvpkotlin.view.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 上午11:31
 * Describe: 发现
 */
class DiscoveryFragment : BaseFragment(){

    private val tabList=ArrayList<String>()

    private val fragments=ArrayList<Fragment>()

    private var mTitle: String? =null

    companion object {
        fun getInstance(title:String):DiscoveryFragment{
            val fragment=DiscoveryFragment()
            val bundle= Bundle()
            fragment.arguments=bundle
            fragment.mTitle=title
            return fragment
        }
    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun initView() {

        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it,toolbar) }

        tv_header_title.text=mTitle

        tabList.add("关注")
        tabList.add("分类")

        fragments.add(FollowFragment.getInstance("关注"))
        fragments.add(CategoryFragment.getInstance("分类"))

        mViewPager.adapter=BaseFragmentAdapter(childFragmentManager,fragments,tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)

    }
}