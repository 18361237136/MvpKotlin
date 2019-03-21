package com.example.mvpkotlin.mvp.model.bean

import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/21 上午10:14
 * Describe:搜索MOdel
 */
class SearchModel {


    /**
     * 请求热门关键词的数据
     */
    fun requestHotWordData(): Observable<ArrayList<String>> {
        return RetrofitManager.service.getHotWord()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 搜索关键词返回的结果
     */
    fun getSearchResult(words:String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getSearchData(words)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多数据
     */
    fun loadMoreData(url:String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }

}