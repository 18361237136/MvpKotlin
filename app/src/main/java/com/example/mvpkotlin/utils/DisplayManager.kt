package com.example.mvpkotlin.utils

import android.content.Context
import android.util.DisplayMetrics

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/6 上午9:54
 * Describe:图片大小管理
 */
object DisplayManager {
    init {

    }

    private var displayMetrics: DisplayMetrics?=null

    private var screenWidth:Int?=null

    private var screenHeight:Int?=null

    private var screenDpi:Int?=null

    fun init(context: Context){
        displayMetrics=context.resources.displayMetrics
        screenWidth= displayMetrics?.widthPixels
        screenHeight= displayMetrics?.heightPixels
        screenDpi= displayMetrics?.densityDpi
    }

    //UI图的大小
    private const val STANDARD_WIDTH=1080
    private const val STANDARD_HEIGHT=1920

    fun getScreenWidth():Int?{
        return screenWidth
    }

    fun getScreenHeight():Int?{
        return screenHeight
    }

    /**
     * 传入UI图中问题的高度，单位像素
     */
    fun getPaintSize(size:Int):Int?{
        return DisplayManager.getRealHeight(size)
    }

    /**
     * 输出图片的实际宽度
     */
    fun getRealWidth(px:Int):Int?{
        return DisplayManager.getRealWidth(px, STANDARD_WIDTH.toFloat())
    }

    fun getRealWidth(px:Int,parentWidth:Float):Int?{
        return (px/parentWidth* getScreenWidth()!!).toInt()
    }

    /**
     * 输出图片的实际高度
     */
    fun getRealHeight(px:Int):Int?{
        return DisplayManager.getRealWidth(px, STANDARD_WIDTH.toFloat())
    }

    fun getRealHeight(px:Int,parentHeight:Float):Int?{
        return (px/parentHeight* getScreenHeight()!!).toInt()
    }

    /**
     * dip转px
     */
    fun dip2px(dipValue:Float):Int?{
        val scale= displayMetrics?.density
        return (dipValue*scale!!+0.5f).toInt()
    }


}
