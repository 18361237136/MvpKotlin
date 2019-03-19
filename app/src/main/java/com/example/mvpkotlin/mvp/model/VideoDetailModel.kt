package com.example.mvpkotlin.mvp.model

import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/18 下午7:28
 * Describe: 视频相关参数
 */
class VideoDetailModel {
    fun requestRelatedData(id:Long):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getRelatedData(id)
                .compose(SchedulerUtils.ioToMain())
    }
}