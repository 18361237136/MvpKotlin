package com.example.mvpkotlin.rx.scheduler

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午1:28
 * Describe:
 */
object SchedulerUtils {
    fun<T> ioToMain():IoMainScheduler<T>{
        return IoMainScheduler()
    }
}