package com.example.mvpkotlin.mvp.model

import com.example.mvpkotlin.mvp.model.bean.CategoryBean
import com.example.mvpkotlin.network.RetrofitManager
import com.example.mvpkotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午2:12
 * Describe:分类
 */
class CategoryModel {

    //获取分类信息
    fun getCategoryData():Observable<ArrayList<CategoryBean>>{
        return RetrofitManager.service.getCategory()
                .compose(SchedulerUtils.ioToMain())
    }
}