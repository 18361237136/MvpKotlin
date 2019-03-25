package com.example.mvpkotlin.network.api

import com.example.mvpkotlin.mvp.model.bean.CategoryBean
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

    /**
     * 热门搜索词
     */
    @GET("v3/queries/hot")
    fun getHotWord():Observable<ArrayList<String>>

    /**
     * 获取搜索信息
     */
    @GET("v1/search?&num=10&start=10")
    fun getSearchData(@Query("query") query: String):Observable<HomeBean.Issue>

    /**
     * 获取更多的 Issue
     */
    @GET
    fun getIssueData(@Url url: String): Observable<HomeBean.Issue>

    /**
     * 关注
     */
    @GET("v4/tabs/follow")
    fun getFollowInfo():Observable<HomeBean.Issue>

    /**
     * 获取分类
     */
    @GET("v4/categories")
    fun getCategory(): Observable<ArrayList<CategoryBean>>

    /**
     * 获取分类详情List
     */
    @GET("v4/categories/videoList?")
    fun getCategoryDetailList(@Query("id") id: Long): Observable<HomeBean.Issue>

}

