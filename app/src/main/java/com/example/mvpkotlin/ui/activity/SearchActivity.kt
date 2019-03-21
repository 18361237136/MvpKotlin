package com.example.mvpkotlin.ui.activity

import android.annotation.TargetApi
import android.graphics.Typeface
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AnimationUtils
import com.example.mvpkotlin.MyApplication
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseActivity
import com.example.mvpkotlin.mvp.contract.SearchContract
import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.mvp.presenter.SearchPresenter
import com.example.mvpkotlin.ui.adapter.CategoryDetailAdapter
import com.example.mvpkotlin.view.ViewAnimUtils
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/19 下午8:49
 * Describe:搜索页面
 */
class SearchActivity :BaseActivity(),SearchContract.View{

    private val mPresenter by lazy { SearchPresenter() }

    private val mResultAdapter by lazy { CategoryDetailAdapter(this, itemList, R.layout.item_category_detail) }

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private var mTextTypeface: Typeface? = null

    //是否加载更多
    private var loadingMore=false

    init {
        mPresenter.attachView(this)
        //细黑简体字体
        mTextTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun layoutId(): Int= R.layout.activity_search

    override fun initData() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            setUpEnterAnimation()
            setUpExitAnimation()
        }else{
            setUpView()
        }
    }

    override fun initUI() {
        tv_title_tip.typeface=mTextTypeface
        tv_hot_search_words.typeface=mTextTypeface

        //初始化查询结果的RecyclerView
        mRecyclerView_result.layoutManager=LinearLayoutManager(this)
        mRecyclerView_result.adapter=mResultAdapter
    }

    /**
     * 进场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpEnterAnimation(){
        val transition=TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion)
        window.sharedElementEnterTransition=transition
        transition.addListener(object :Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                animateRevealShow()
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

    /**
     * 退场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpExitAnimation() {
        val fade = Fade()
        window.returnTransition = fade
        fade.duration = 300
    }

    /**
     * 展示动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animateRevealShow(){
        ViewAnimUtils.animateRevealShow(this,rel_frame,fab_circle.width/2,
                R.color.backgroundColor,object :ViewAnimUtils.OnRevealAnimationListener{
            override fun onRevealHide() {
            }

            override fun onRevealShow() {
                setUpView()
            }

        })
    }

    //创建界面
    private fun setUpView(){
        val animation=AnimationUtils.loadAnimation(this,android.R.anim.fade_in)
        animation.duration=300
        rel_container.startAnimation(animation)
        rel_container.visibility= View.VISIBLE
        openKeyBoard(et_search_view,applicationContext)
    }

    override fun setHotWordData(string: ArrayList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSearchResult(issue: HomeBean.Issue) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeSoftKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEmptyView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}