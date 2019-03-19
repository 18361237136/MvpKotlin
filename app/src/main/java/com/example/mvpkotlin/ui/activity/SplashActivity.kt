package com.example.mvpkotlin.ui.activity

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.mvpkotlin.MyApplication
import com.example.mvpkotlin.R
import com.example.mvpkotlin.base.BaseActivity
import com.example.mvpkotlin.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity :BaseActivity(){

    private var textTypeface:Typeface?=null

    private var decTypeface:Typeface?=null

    private var alphaAnimation:AlphaAnimation?=null

    init {
        textTypeface=Typeface.createFromAsset(MyApplication.context.assets,"fonts/Lobster-1.4.otf")
        decTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }


    override fun initData() {
    }

    override fun start() {
    }

    override fun layoutId(): Int= R.layout.activity_splash

    override fun initUI() {
        tv_app_name.typeface=textTypeface
        tv_splash_desc.typeface=decTypeface
        tv_version_name.text="v${AppUtils.getVersionName(this)}"

        //渐变展示启动屏
        alphaAnimation= AlphaAnimation(0.3f,1.0f)
        alphaAnimation?.duration=2000
        alphaAnimation?.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation) {
            }

            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                redirectTo()
            }

        })

        checkPermission()
    }

    fun redirectTo(){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * 6.0以下版本不会弹框
     *
     */
    private fun checkPermission(){
        val perms= arrayOf(Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this,"MvpKotlin需要以下权限,请允许",0,*perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if(requestCode==0){
            if(perms.isNotEmpty()){
                if(perms.contains(Manifest.permission.READ_PHONE_STATE)&&perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    if(alphaAnimation!=null){
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }
}