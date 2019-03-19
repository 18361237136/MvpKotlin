package com.example.mvpkotlin.network.exception

import java.lang.RuntimeException

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午3:00
 * Describe:
 */
class ApiException :RuntimeException{

    private var code:Int?=null

    constructor(throwable:Throwable,code:Int):super(throwable){
        this.code=code
    }

    constructor(message:String):super(Throwable(message))
}