package com.example.mvpkotlin.mvp.contract

import com.example.mvpkotlin.base.IBaseView
import com.example.mvpkotlin.base.IPresenter
import com.example.mvpkotlin.mvp.model.bean.CategoryBean

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午12:55
 * Describe:分类
 */
interface CategoryContract {

    interface View: IBaseView {

        //显示分类的信息
        fun showCategory(categoryList:ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {
        //获取分类信息
        fun getCategoryData()
    }
}