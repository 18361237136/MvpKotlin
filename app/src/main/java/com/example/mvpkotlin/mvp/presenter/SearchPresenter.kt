package com.example.mvpkotlin.mvp.presenter

import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.mvp.contract.SearchContract
import com.example.mvpkotlin.mvp.model.bean.SearchModel
import com.example.mvpkotlin.network.exception.ExceptionHandle

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/20 上午10:01
 * Describe:搜索
 */
class SearchPresenter :BasePresenter<SearchContract.View>(),SearchContract.Presenter{

    private var nextPageUrl:String?=null

    private val searchModel by lazy { SearchModel() }

    //获取热门关键词
    override fun requestHotWordData() {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = searchModel.requestHotWordData()
                .subscribe({string->
                    mRootView?.apply {
                        setHotWordData(string)
                    }
                },{throwable->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                }))
    }

    /**
     * 查询关键词
     */
    override fun querySearchData(words: String) {
        checkViewAttached()
        mRootView?.apply {
            closeSoftKeyboard()
            showLoading()
        }
        addSubscription(disposable = searchModel.getSearchResult(words)
                .subscribe({issue->
                    mRootView?.apply {
                        dismissLoading()
                        if(issue.count>0&&issue.itemList.size>0){
                            nextPageUrl=issue.nextPageUrl
                            setSearchResult(issue)
                        }else{
                            setEmptyView()
                        }
                    }
                },{throwable->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                }))
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        checkViewAttached()
        nextPageUrl?.let {
            addSubscription(disposable = searchModel.loadMoreData(it)
                    .subscribe({issue->
                        mRootView?.apply {
                            nextPageUrl=issue.nextPageUrl
                            setSearchResult(issue)
                        }
                    },{throwable->
                        mRootView?.apply {
                            //处理异常
                            showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                        }
                    }))
        }

    }
}