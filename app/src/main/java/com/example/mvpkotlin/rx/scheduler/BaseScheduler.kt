package com.example.mvpkotlin.rx.scheduler

import io.reactivex.*
import org.reactivestreams.Publisher

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午1:31
 * Describe:RxJava中基础相关类
 */
abstract class BaseScheduler<T> protected constructor(private val subscribeOnScheduler:Scheduler,
                                                      private val observeOnScheduler: Scheduler):ObservableTransformer<T,T>, SingleTransformer<T, T>,
        MaybeTransformer<T, T>,
        CompletableTransformer,
        FlowableTransformer<T, T> {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
    }

}