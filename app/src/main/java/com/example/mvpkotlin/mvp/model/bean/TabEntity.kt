package com.example.mvpkotlin.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 上午11:08
 * Describe:底部菜单Entity
 */
class TabEntity(var title:String,private var selectedIcon:Int,private var unSelectedIcon:Int):CustomTabEntity {
    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }
}