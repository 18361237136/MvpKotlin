package com.example.mvpkotlin.ui.fragment

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseFragment
import com.example.mvpkotlin.mvp.contract.CategoryContract
import com.example.mvpkotlin.mvp.model.bean.CategoryBean
import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.mvp.presenter.CategoryPresenter
import com.example.mvpkotlin.network.exception.ErrorStatus
import com.example.mvpkotlin.showToast
import com.example.mvpkotlin.ui.adapter.CategoryAdapter
import com.example.mvpkotlin.ui.adapter.CategoryDetailAdapter
import com.example.mvpkotlin.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午2:29
 * Describe:分类
 */
class CategoryFragment :BaseFragment(),CategoryContract.View{

    private val mPresenter by lazy { CategoryPresenter() }

    private val mAdapter by lazy { activity?.let {  CategoryAdapter(it, mCategoryList, R.layout.item_category)} }

    private var mCategoryList = ArrayList<CategoryBean>()

    private var mTitle:String?=null

    companion object {
        fun getInstance(title:String):CategoryFragment{
            val fragment=CategoryFragment()
            val bundle=Bundle()
            fragment.arguments=bundle
            fragment.mTitle=title
            return fragment
        }
    }

    override fun lazyLoad() {
        mPresenter.getCategoryData()
    }

    override fun getLayoutId(): Int =R.layout.fragment_category

    override fun initView() {
        mPresenter.attachView(this)

        mLayoutStatusView=multipleStatusView

        mRecyclerView.adapter=mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(activity,2)
        mRecyclerView.addItemDecoration(object :RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position=parent.getChildPosition(view)
                val offset=DisplayManager.dip2px(2f)!!
                outRect.set(if(position%2==0) 0 else offset,offset,
                        if (position % 2 == 0) offset else 0, offset)
            }
        })
    }

    override fun showCategory(categoryList: ArrayList<CategoryBean>) {
        mCategoryList = categoryList
        mAdapter?.setData(mCategoryList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()
        } else {
            multipleStatusView?.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView?.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView?.showContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}