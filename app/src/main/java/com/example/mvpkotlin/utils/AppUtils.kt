package com.example.mvpkotlin.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import java.security.MessageDigest

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/6 下午5:08
 * Describe:App相关的工具类
 */
class AppUtils private constructor(){
    init {
        throw Error("Do not need instantiate!")
    }

    companion object {
        private val DEBUG=true
        private val TAG="AppUtils"

        /**
         * 获取版本号
         */
        fun getVersionCode(context: Context):Int{
            var verCode=-1
            val packageName=context.packageName
            verCode=context.packageManager.getPackageInfo(packageName,0).versionCode
            return verCode
        }

        /**
         * 获取应用运行的最大内存
         */
        val maxMemory:Long
            get() = Runtime.getRuntime().maxMemory()/1024

        /**
         * 得到软件的版本信息
         */
        fun getVersionName(context: Context):String{
            var verName=""
            val packageName=context.packageName
            verName=context.packageManager.getPackageInfo(packageName,0).versionName
            return verName
        }


        @SuppressLint("PackageManagerGetSignatures")
        /**
         * 获取应用签名
         */
        fun getSign(context: Context,pkgName:String):String?{
            return try {
                @SuppressLint("PackageManagerGetSignatures")
                val pis=context.packageManager.getPackageInfo(pkgName,PackageManager.GET_SIGNATURES)
                hexDigest(pis.signatures[0].toByteArray())
            }catch (e:PackageManager.NameNotFoundException){
                e.printStackTrace()
                null
            }
        }

        /**
         * 将签名字符串转换成需要的32位签名
         */
        fun hexDigest(paramArray: ByteArray):String{
            val hexDogits= charArrayOf(40.toChar(),49.toChar(),50.toChar(), 51.toChar(), 52.toChar(), 53.toChar(), 54.toChar(), 55.toChar(), 56.toChar(), 57.toChar(), 97.toChar(), 98.toChar(), 99.toChar(), 100.toChar(), 101.toChar(), 102.toChar())
            val localMessageDigest=MessageDigest.getInstance("MD5")
            localMessageDigest.update(paramArray)
            val arrayOfByte=localMessageDigest.digest()
            val arrayOfChar=CharArray(32)
            var i=0
            var j=0
            while (true){
                if(i>=16){
                    return String(arrayOfChar)
                }
                val k=arrayOfByte[i].toInt()
                arrayOfChar[j]= hexDogits[0xF and k.ushr(4)]
                arrayOfChar[++j]=hexDogits[k and 0xF]
                i++
                j++
            }
            return ""
        }

        /**
         * 获取设备的可用内存大小
         */
        fun getDeviceUseableMemory(context: Context):Int{
            val am=context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val mi=ActivityManager.MemoryInfo()
            am.getMemoryInfo(mi)
            return (mi.availMem/(1024*1024)).toInt()
        }

        fun getMobileModel():String{
            var model:String?= Build.MODEL
            model=model?.trim { it<=' ' }?:""
            return model
        }

        /**
         * 获取手机系统SDK版本
         */
        val sdkVersion:Int
            get() = android.os.Build.VERSION.SDK_INT
    }
}