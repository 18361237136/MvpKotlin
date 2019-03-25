package com.example.mvpkotlin.mvp.contract

import com.example.mvpkotlin.base.IBaseView
import com.example.mvpkotlin.base.IPresenter
import com.example.mvpkotlin.mvp.model.bean.HomeBean

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 上午10:46
 * Describe:关注
 */
interface FollowContract {

    interface View: IBaseView {
        //设置关注信息
        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {
        //获取List
        fun requestFollowList()

        //加载更多
        fun loadMoreData()
    }
}