package com.example.mvpkotlin.mvp.presenter

import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.mvp.contract.CategoryContract
import com.example.mvpkotlin.mvp.model.CategoryModel
import com.example.mvpkotlin.network.exception.ExceptionHandle

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午2:34
 * Describe:分类
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter{

    private val categoryModel:CategoryModel by lazy { CategoryModel() }

    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable=categoryModel.getCategoryData()
                .subscribe({categoryList->
                    mRootView?.apply {
                        dismissLoading()
                        showCategory(categoryList)
                    }
                },{t->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

}

