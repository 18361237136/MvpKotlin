package com.example.mvpkotlin.mvp.model.bean

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/25 下午6:07
 * Describe:
 */
data class TabInfoBean(val tabInfo:TabInfo) {
    data class TabInfo(val tabList:ArrayList<Tab>)

    data class Tab(val id:Long,val name:String,val apiUrl:String)
}