package com.example.mvpkotlin.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/8 下午7:26
 * Describe:
 */
class NetWorkUtil {
    companion object {
        var NET_CNNT_BAIDU_OK = 1 // NetworkAvailable
        var NET_CNNT_BAIDU_TIMEOUT = 2 // no NetworkAvailable
        var NET_NOT_PREPARE = 3 // Net no ready
        var NET_ERROR = 4 //net error

        private val TIMEOUT = 3000 // TIMEOUT

        fun isNetworkAvailable(context: Context):Boolean{
            val manager=context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info=manager.activeNetworkInfo
            return !(info==null||!info.isAvailable)
        }
    }
}