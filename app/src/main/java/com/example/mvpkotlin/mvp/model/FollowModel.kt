package com.example.mvpkotlin.mvp.model

import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 上午10:52
 * Describe:关注Model
 */
class FollowModel {

    /**
     * 获取关注信息
     */
    fun requestFollowList():Observable<HomeBean.Issue>{
        return RetrofitManager.service.getFollowInfo()
                .compose(SchedulerUtils.ioToMain())
    }

    //加载更多
    fun loadMoreData(url:String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}