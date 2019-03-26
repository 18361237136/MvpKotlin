package com.example.mvpkotlin.ui.activity

import android.content.Intent
import android.net.Uri
import com.example.mvpkotlin.MyApplication
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseActivity
import com.example.mvpkotlin.utils.AppUtils
import com.example.mvpkotlin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/26 上午10:39
 * Describe:关于
 */
class AboutActivity :BaseActivity() {
    override fun initUI() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        tv_version_name.text ="v${AppUtils.getVersionName(MyApplication.context)}"
        //返回
        toolbar.setNavigationOnClickListener { finish() }

        //访问GitHub
        relayout_gitHub.setOnClickListener {
            val uri= Uri.parse("https://github.com/18361237136/MvpKotlin/tree/master")
            val intent=Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
    }

    override fun start() {
    }

    override fun layoutId(): Int= R.layout.activity_about

    override fun initData() {

    }
}