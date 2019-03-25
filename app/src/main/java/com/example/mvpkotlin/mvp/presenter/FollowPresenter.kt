package com.example.mvpkotlin.mvp.presenter

import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.mvp.contract.FollowContract
import com.example.mvpkotlin.mvp.model.FollowModel
import com.example.mvpkotlin.network.exception.ExceptionHandle

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 上午10:49
 * Describe:获取关注信息
 */
class FollowPresenter :BasePresenter<FollowContract.View>(),FollowContract.Presenter{

    private val followModel by lazy { FollowModel() }

    private var nextPageUrl:String?=null

    //获取关注数据
    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable=followModel.requestFollowList()
                .subscribe({issue->
                    mRootView?.apply {
                        dismissLoading()
                        nextPageUrl=issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                },{throwable->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun loadMoreData() {
        val disposable=nextPageUrl?.let {
            followModel.loadMoreData(it)
                    .subscribe({issue->
                        mRootView?.apply {
                            nextPageUrl=issue.nextPageUrl
                            setFollowInfo(issue)
                        }
                    },{t->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                        }
                    })
        }

        if(disposable!=null){
            addSubscription(disposable)
        }
    }

}