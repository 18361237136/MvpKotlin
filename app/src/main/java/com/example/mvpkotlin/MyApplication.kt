package com.example.mvpkotlin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.mvpkotlin.utils.DisplayManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/5 下午8:08
 * Describe: 自定义Application
 */
class MyApplication :Application(){

    private var refWatcher:RefWatcher?=null
    companion object {
        private val TAG="MyApplication"

        var context: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication=context.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        initConfig()
        DisplayManager.init(this)
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    private fun setupLeakCanary():RefWatcher{
        return if (LeakCanary.isInAnalyzerProcess(this)){
            RefWatcher.DISABLED
        }else{
            LeakCanary.install(this)
        }
    }

    /**
     * 初始化配置
     */
    private fun initConfig(){
        val formatStrategy=PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)//隐藏线程信息 默认显示
                .methodCount(0)//决定打印多少行(每行代表一个方法)默认2
                .methodOffset(7)
                .tag("hao_zz")
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

    }

    private val mActivityLifecycleCallbacks=object:Application.ActivityLifecycleCallbacks{
        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

    }
}