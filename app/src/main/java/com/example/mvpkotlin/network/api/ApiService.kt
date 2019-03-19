package com.example.mvpkotlin.network.api

import com.example.mvpkotlin.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/8 上午11:36
 * Describe:Api接口
 */
interface ApiService {

    /**
     * 首页精选
     */
    @GET("v2/feed?")
    fun getFirstHomeData(@Query("num") num:Int):Observable<HomeBean>

    /**
     * 根据nextPageUrl请求下一页数据
     */
    @GET
    fun getMoreHomeData(@Url url:String):Observable<HomeBean>

    /**
     * 根据item id获取相关视频
     */
    @GET("v4/video/related?")
    fun getRelatedData(@Query("id") id:Long):Observable<HomeBean.Issue>
}

