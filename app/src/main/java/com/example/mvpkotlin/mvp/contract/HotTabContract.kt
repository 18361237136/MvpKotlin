package com.example.mvpkotlin.mvp.contract

import com.example.mvpkotlin.base.IBaseView
import com.example.mvpkotlin.base.IPresenter
import com.example.mvpkotlin.mvp.model.bean.TabInfoBean

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午5:46
 * Describe:热门
 */
interface HotTabContract {

    interface View: IBaseView {
        //设置TabInfo
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {
        //获取TabInfo
        fun getTabInfo()
    }
}