package com.example.mvpkotlin.mvp.contract

import com.example.mvpkotlin.base.IBaseView
import com.example.mvpkotlin.base.IPresenter
import com.example.mvpkotlin.mvp.model.bean.HomeBean

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午4:23
 * Describe:分类详情
 */
interface CategoryDetailContract {

    interface View: IBaseView {

        //设置列表数据
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)
    }

    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}