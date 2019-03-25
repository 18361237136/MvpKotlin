package com.example.mvpkotlin.mvp.model.bean

import java.io.Serializable

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午2:09
 * Describe:分类
 */
data class CategoryBean(val id:Long,val name:String,val description:String,val bgPicture: String, val bgColor: String, val headerImage: String):Serializable