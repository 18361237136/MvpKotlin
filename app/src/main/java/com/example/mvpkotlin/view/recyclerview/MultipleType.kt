package com.example.mvpkotlin.view.recyclerview

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午4:48
 * Describe:多布局条目类型
 */
interface MultipleType<in T> {
    fun getLayoutId(item:T,position:Int):Int
}