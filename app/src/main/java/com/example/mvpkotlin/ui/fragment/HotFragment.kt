package com.example.mvpkotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseFragment
import com.example.mvpkotlin.base.BaseFragmentAdapter
import com.example.mvpkotlin.mvp.contract.HotTabContract
import com.example.mvpkotlin.mvp.model.bean.TabInfoBean
import com.example.mvpkotlin.mvp.presenter.HotTabPresenter
import com.example.mvpkotlin.network.exception.ErrorStatus
import com.example.mvpkotlin.showToast
import com.example.mvpkotlin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 上午11:31
 * Describe:热门
 */
class HotFragment : BaseFragment(),HotTabContract.View{

    private val mPresenter by lazy { HotTabPresenter() }

    private var mTitle:String?=null

    //存放tab标题
    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()

    companion object {
        fun getInstance(title:String):HotFragment{
            val fragment=HotFragment()
            val bundle= Bundle()
            fragment.arguments=bundle
            fragment.mTitle=title
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun lazyLoad() {
        mPresenter.getTabInfo()
    }

    override fun initView() {
        mLayoutStatusView=multipleStatusView
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it,toolbar) }
    }

    override fun setTabInfo(tabInfoBean: TabInfoBean) {
        multipleStatusView.showContent()

        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,mFragmentList,mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
    }

    override fun showError(errorMsg: String,errorCode:Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}