package com.example.mvpkotlin.view.recyclerview.adapter

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午5:31
 * Describe:Adapter条目的长按事件
 */
interface OnItemLongClickListener {
    fun onItemLongClick(obj:Any?,position:Int):Boolean
}