package com.example.mvpkotlin.mvp.presenter

import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.mvp.contract.CategoryDetailContract
import com.example.mvpkotlin.mvp.model.CategoryDetailModel

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午4:33
 * Describe:分类详情
 */
class CategoryDetailPresenter : BasePresenter<CategoryDetailContract.View>(),CategoryDetailContract.Presenter {

    private val categoryDetailModel by lazy {
        CategoryDetailModel()
    }

    private var nextPageUrl:String?=null

    override fun getCategoryDetailList(id: Long) {
        checkViewAttached()
        val disposable= categoryDetailModel.getCategoryDetailList(id)
                .subscribe({
                    issue ->
                    mRootView?.apply {
                        nextPageUrl = issue.nextPageUrl
                        setCateDetailList(issue.itemList)
                    }
                },{
                    throwable ->
                    mRootView?.apply {
                        showError(throwable.toString())
                    }
                })

        addSubscription(disposable)
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            categoryDetailModel.loadMoreData(it)
                    .subscribe({ issue ->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setCateDetailList(issue.itemList)
                        }
                    }, { throwable ->
                        mRootView?.apply {
                            showError(throwable.toString())
                        }
                    })
        }

        disposable?.let { addSubscription(it) }
    }
}