package com.example.mvpkotlin

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/11 下午8:08
 * Describe:常量
 */
class Constants private constructor(){
    companion object {
        val BUNDLE_VIDEO_DATA = "video_data"
        val BUNDLE_CATEGORY_DATA = "category_data"

        //sp 存储的文件名
        val FILE_WATCH_HISTORY_NAME = "watch_history_file"   //观看记录
    }
}