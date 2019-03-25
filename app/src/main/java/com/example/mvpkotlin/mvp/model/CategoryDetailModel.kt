package com.example.mvpkotlin.mvp.model

import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午4:31
 * Describe:分类详情Model
 */
class CategoryDetailModel {

    /**
     * 获取分类下的 List 数据
     */
    fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getCategoryDetailList(id)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多数据
     */
    fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}