package com.example.mvpkotlin.mvp.contract

import com.example.mvpkotlin.base.IBaseView
import com.example.mvpkotlin.base.IPresenter
import com.example.mvpkotlin.mvp.model.bean.HomeBean

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 下午7:14
 * Describe: home契约类
 */
interface HomeContract {
    interface View:IBaseView{

        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList:ArrayList<HomeBean.Issue.Item>)

        /**
         * 显示错误信息
         */
        fun showError(msg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{

        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num:Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData()
    }
}