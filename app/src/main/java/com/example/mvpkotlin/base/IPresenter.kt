package com.example.mvpkotlin.base

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 下午7:21
 * Describe: Presenter基类
 */
interface IPresenter <in V:IBaseView>{

    fun attachView(mRootView:V)

    fun detachView()
}