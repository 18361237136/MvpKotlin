package com.example.mvpkotlin.mvp.model

import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午7:19
 * Describe:排行榜Model
 */
class RankModel {

    //获取排行榜
    fun requestRankList(apiUrl:String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getIssueData(apiUrl)
                .compose(SchedulerUtils.ioToMain())
    }
}