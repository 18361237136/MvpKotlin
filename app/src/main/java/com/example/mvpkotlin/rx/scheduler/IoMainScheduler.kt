package com.example.mvpkotlin.rx.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午1:30
 * Describe:
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(),AndroidSchedulers.mainThread()){
}