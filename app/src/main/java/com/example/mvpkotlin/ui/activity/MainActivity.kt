package com.example.mvpkotlin.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseActivity
import com.example.mvpkotlin.mvp.model.bean.TabEntity
import com.example.mvpkotlin.showToast
import com.example.mvpkotlin.ui.fragment.DiscoveryFragment
import com.example.mvpkotlin.ui.fragment.HomeFragment
import com.example.mvpkotlin.ui.fragment.HotFragment
import com.example.mvpkotlin.ui.fragment.MineFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/6 下午7:17
 * Describe: 主界面
 */
class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    //未选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)

    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    //默认为0
    private var mIndex = 0

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null
    private var mDiscoveryFragment: DiscoveryFragment? = null
    private var mHotFragment: HotFragment? = null
    private var mMineFragment: MineFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab=mIndex
        switchFragment(mIndex)
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置防止崩溃activity系统回收时，fragment错乱
        if(tab_layout!=null){
            outState.putInt("currTabIndex",mIndex)
        }
    }

    override fun initData() {
    }

    override fun initUI() {
    }

    override fun start() {
    }

    override fun layoutId(): Int = R.layout.activity_main

    /**
     * 初始化底部菜单
     */
    private fun initTab() {
        //mapTo：转化
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {

            }

            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }
        })
    }

    /**
     * 切换fragment
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 ->
                mHomeFragment?.let {
                    transaction.show(it)
                } ?: HomeFragment.getInstance(mTitles[position]).let {
                    mHomeFragment = it
                    transaction.add(R.id.fl_container, it, "home")
                }

            1 ->
                mDiscoveryFragment?.let {
                    transaction.show(it)
                } ?: DiscoveryFragment.getInstance(mTitles[position]).let {
                    mDiscoveryFragment = it
                    transaction.add(R.id.fl_container, it, "discovery")
                }
            2 ->
                mHotFragment?.let {
                    transaction.show(it)
                } ?: HotFragment.getInstance(mTitles[position]).let {
                    mHotFragment = it
                    transaction.add(R.id.fl_container, it, "hot")
                }

            3 ->
                mMineFragment?.let {
                    transaction.show(it)
                } ?: MineFragment.getInstance(mTitles[position]).let {
                    mMineFragment = it
                    transaction.add(R.id.fl_container, it, "mine")
                }
        }
        mIndex=position
        tab_layout.currentTab=mIndex
        transaction.commitNowAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    private var mExitTime:Long=0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis().minus(mExitTime)<=2000){
                finish()
            }else{
                mExitTime=System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}