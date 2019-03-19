package com.example.mvpkotlin.ui.activity

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.view.ViewCompat
import android.transition.Transition
import android.widget.ImageView
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseActivity
import com.example.mvpkotlin.glide.GlideApp
import com.example.mvpkotlin.listener.VideoListener
import com.example.mvpkotlin.mvp.contract.VideoDetailContract
import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.mvp.presenter.VideoDetailPresenter
import com.example.mvpkotlin.showToast
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.header.MaterialHeader
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
import kotlinx.android.synthetic.main.activity_video_detail.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/11 下午7:57
 * Describe:视频详情
 */
class VideoDetailActivity :BaseActivity(),VideoDetailContract.View{

    companion object {
        const val IMG_TRANSITION = "IMG_TRANSITION"
        const val TRANSITION = "TRANSITION"
    }

    /**
     * 第一次调用的时候初始化
     */
    private val mPresenter by lazy{ VideoDetailPresenter()}

    /**
     * Item详细数据
     */
    private lateinit var itemData:HomeBean.Issue.Item
    private var orientationUtils: OrientationUtils?=null

    private var isPlay:Boolean=false
    private var isPause:Boolean=false

    private var isTransition:Boolean=false

    private var transition: Transition?=null
    private var mMaterialHeader:MaterialHeader?=null

    override fun initData() {
    }

    override fun initUI() {

        mPresenter.attachView(this)
        //过渡动画
        initTransition()
        initVideoViewConfig()
    }

    /**
     * 初始化VideoVide的配置
     */
    private fun initVideoViewConfig(){
        //设置旋转
        orientationUtils= OrientationUtils(this,mVideoView)
        //是否旋转
        mVideoView.isRotateViewAuto=false
        //是否可以滑动调整
        mVideoView.setIsTouchWiget(true)

        //增加封面
        val imageView=ImageView(this)
        imageView.scaleType=ImageView.ScaleType.CENTER_CROP
        GlideApp.with(this)
                .load(itemData.data?.cover?.feed)
                .centerCrop()
                .into(imageView)
        mVideoView.thumbImageView=imageView

        mVideoView.setStandardVideoAllCallBack(object :VideoListener{
            override fun onPrepared(url: String?, vararg objects: Any?) {
                super.onPrepared(url, *objects)
                orientationUtils?.isEnable=true
                isPlay=true
            }

            override fun onAutoComplete(url: String, vararg objects: Any) {
                super.onAutoComplete(url, *objects)
                Logger.d("***** onAutoPlayComplete **** ")
            }

            override fun onPlayError(url: String, vararg objects: Any) {
                super.onPlayError(url, *objects)
                showToast("播放失败")
            }

            override fun onEnterFullscreen(url: String, vararg objects: Any) {
                super.onEnterFullscreen(url, *objects)
                Logger.d("***** onEnterFullscreen **** ")
            }

            override fun onQuitFullscreen(url: String, vararg objects: Any) {
                super.onQuitFullscreen(url, *objects)
                Logger.d("***** onQuitFullscreen **** ")
                //列表返回的样式判断
                orientationUtils?.backToProtVideo()
            }
        })

        mVideoView.backButton.setOnClickListener({  })
    }

    override fun start() {
    }

    override fun layoutId(): Int = R.layout.activity_video_detail

    private fun initTransition(){
        if(isTransition&& Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            postponeEnterTransition()
            ViewCompat.setTransitionName(mVideoView, IMG_TRANSITION)
            addTransitionListener()
            startPostponedEnterTransition()
        }else{
            loadVideoInfo()
        }
    }

    //加载视频信息
    fun loadVideoInfo(){
        mPresenter.loadVideoInfo(itemData)
    }

    override fun setVideo(url: String) {
    }

    override fun setVideoInfo(itemInfo: HomeBean.Issue.Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBackground(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRecentRelatedVideo(itemList: ArrayList<HomeBean.Issue.Item>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setErrorMsg(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //监听返回键
    override fun onBackPressed() {
        super.onBackPressed()
        orientationUtils?.backToProtVideo()
        if(StandardGSYVideoPlayer.backFromWindowFull(this)){
            return
        }
        mVideoView.setStandardVideoAllCallBack(null)
        GSYBaseVideoPlayer.releaseAllVideos()
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) run {
            super.onBackPressed()
        } else {
            finish()
            overridePendingTransition(R.anim.anim_out, R.anim.anim_in)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addTransitionListener(){
        transition=window.sharedElementEnterTransition
        transition?.addListener(object :Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition?) {
                Logger.d("onTransitionEnd()------")

                loadVideoInfo()
                transition?.removeListener(this)
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }

        })
    }

}