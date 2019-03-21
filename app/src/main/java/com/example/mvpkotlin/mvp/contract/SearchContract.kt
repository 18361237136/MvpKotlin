package com.example.mvpkotlin.mvp.contract

import com.example.mvpkotlin.base.IBaseView
import com.example.mvpkotlin.base.IPresenter
import com.example.mvpkotlin.mvp.model.bean.HomeBean

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/19 下午8:55
 * Describe: 搜索contract
 */
interface SearchContract {

    interface View:IBaseView{

        //设置热门关键词数据
        fun setHotWordData(string:ArrayList<String>)

        //设置搜索关键词返回的结果
        fun setSearchResult(issue:HomeBean.Issue)

        //关闭软键盘
        fun closeSoftKeyboard()

        //设置空View
        fun setEmptyView()

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{

        //获取热门关键字的数据
        fun requestHotWordData()

        //查询搜索
        fun querySearchData(words:String)

        //加载更多
        fun loadMoreData()
    }
}