package com.example.mvpkotlin.mvp.model

import com.example.mvpkotlin.mvp.model.bean.TabInfoBean
import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午6:11
 * Describe:热门
 */
class HotTabModel {

    fun getTabInfo():Observable<TabInfoBean>{
        return RetrofitManager.service.getRankList()
                .compose(SchedulerUtils.ioToMain())
    }
}