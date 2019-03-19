package com.example.mvpkotlin.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.multiple_status_view.MultipleStatusView
import com.example.mvpkotlin.MyApplication
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.StringBuilder

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/5 下午7:47
 */
abstract class BaseActivity :AppCompatActivity(),EasyPermissions.PermissionCallbacks {

    protected var mLayoutStatusView:MultipleStatusView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initUI()
        start()
        initListener()
    }

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化ui
     */
    abstract fun initUI()

    /**
     * 开始请求
     */
    abstract fun start()

    open val mRetryClickListener: View.OnClickListener=View.OnClickListener { start() }

    fun initListener(){
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    /**
     * 加载布局
     */
    abstract fun layoutId():Int

    /**
     * 打开软键盘
     */
    fun openKeyBoard(mEditText:EditText,mContext: Context){
        val imm=mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText,InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBoard(mEditText:EditText,mContext: Context){
        val imm=mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken,0)
    }

    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionResult（）方法
     * 在里面调用EasyPermissions.onRequestPermissionsResult(),实现回调
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<out String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    /**
     * 当权限失败的时候回调
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        val sb=StringBuffer()
        for(str in perms){
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length-2,sb.length,"")
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }

    /**
     * 当权限申请成功的时候回调
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.i("EasyPermissions", "获取成功的权限$perms")
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(this)?.watch(this)
    }
}