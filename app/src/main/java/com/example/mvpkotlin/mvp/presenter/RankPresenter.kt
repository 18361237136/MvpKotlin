package com.example.mvpkotlin.mvp.presenter

import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.mvp.contract.RankContract
import com.example.mvpkotlin.mvp.model.RankModel
import com.example.mvpkotlin.network.exception.ExceptionHandle

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午7:17
 * Describe:热门排行
 */
class RankPresenter : BasePresenter<RankContract.View>(), RankContract.Presenter{

    private val rankModel by lazy { RankModel() }


    /**
     *  请求排行榜数据
     */
    override fun requestRankList(apiUrl: String) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = rankModel.requestRankList(apiUrl)
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        setRankList(issue.itemList)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

}