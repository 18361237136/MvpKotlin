package com.example.mvpkotlin.mvp.presenter

import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.mvp.contract.HotTabContract
import com.example.mvpkotlin.mvp.model.HotTabModel
import com.example.mvpkotlin.network.exception.ExceptionHandle

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午6:14
 * Describe:热门
 */
class HotTabPresenter : BasePresenter<HotTabContract.View>(),HotTabContract.Presenter{

    private val hotModel by lazy { HotTabModel() }

    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable=hotModel.getTabInfo()
                .subscribe({
                    tabInfo->
                    mRootView?.setTabInfo(tabInfo)
                },{
                    throwable->
                    //处理异常
                    mRootView?.showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                })
        addSubscription(disposable)
    }

}