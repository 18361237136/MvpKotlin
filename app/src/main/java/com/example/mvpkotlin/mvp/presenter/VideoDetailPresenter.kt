package com.example.mvpkotlin.mvp.presenter

import android.app.Activity
import com.example.mvpkotlin.MyApplication
import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.dataFormat
import com.example.mvpkotlin.mvp.contract.VideoDetailContract
import com.example.mvpkotlin.mvp.model.VideoDetailModel
import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.network.exception.ExceptionHandle
import com.example.mvpkotlin.showToast
import com.example.mvpkotlin.utils.DisplayManager
import com.example.mvpkotlin.utils.NetWorkUtil

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/18 下午7:21
 * Describe:视频详情
 */
class VideoDetailPresenter :BasePresenter<VideoDetailContract.View>(),VideoDetailContract.Presenter{

    private val videoDetailModel:VideoDetailModel by lazy {
        VideoDetailModel()
    }

    /**
     * 加载视频相关数据
     */
    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {
        val playInfo=itemInfo.data?.playInfo

        val netType=NetWorkUtil.isNetworkAvailable(MyApplication.context)
        //检测是否绑定View
        checkViewAttached()
        if(playInfo!!.size>1){
            //当前情况下是wifi的话就高清播放否则就标清
            if(netType){
                for (i in playInfo){
                    if(i.type=="high") {
                        val playUrl = i.url
                        mRootView?.setVideo(playUrl)
                        break
                    }
                }
            }else{
                for(i in playInfo){
                    if(i.type=="normal"){
                        val playUrl = i.url
                        mRootView?.setVideo(playUrl)
                        (mRootView as Activity).showToast("本次消耗${(mRootView as Activity)
                                .dataFormat(i.urlList[0].size)}流量")
                        break
                    }
                }
            }
        }else{
            mRootView?.setVideo(itemInfo.data.playUrl)
        }

        //设置背景
        val backgroundUrl=itemInfo.data.cover.blurred+"/thumbnail/${DisplayManager.getScreenHeight()!! - DisplayManager.dip2px(250f)!!}x${DisplayManager.getScreenWidth()}"
        backgroundUrl.let { mRootView?.setBackground(it) }

        mRootView?.setVideoInfo(itemInfo)
    }

    /**
     * 请求相关的视频数据
     */
    override fun requestRelatedVideo(id: Long) {
        mRootView?.showLoading()
        val disposable=videoDetailModel.requestRelatedData(id)
                .subscribe({ issue->
                     mRootView?.apply {
                         dismissLoading()
                         setRecentRelatedVideo(issue.itemList)
                     }
                },{t->
                    mRootView?.apply {
                        dismissLoading()
                        setErrorMsg(ExceptionHandle.handleException(t))
                    }
                })
        addSubscription(disposable)
    }
}