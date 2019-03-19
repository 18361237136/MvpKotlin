package com.example.mvpkotlin.mvp.model

import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable
import java.util.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/8 上午11:13
 * Describe:首页精选model
 */
class HomeModel {

    /**
     * 获取首页Banner数据
     */
    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{
        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}