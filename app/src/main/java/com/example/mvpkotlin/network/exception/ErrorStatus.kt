package com.example.mvpkotlin.network.exception

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午2:27
 * Describe:
 */
object ErrorStatus {

    /**
     *响应成功
     */
    @JvmField
    val SUCCESS=0

    /**
     * 未知错误
     */
    @JvmField
    val UNKOWN_ERROR=1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR=1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR=1004

    /**
     * API解析异常(或者第三方数据结构更改)等其他异常
     */
    val API_ERROR=1005
}