package com.example.mvpkotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mvpkotlin.MyApplication
import java.io.*
import kotlin.reflect.KProperty

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/8 下午5:36
 * Describe:kotlin委托属性+SharedPreference实例
 */
class Preference<T>(val name:String,private val default:T) {

    companion object {
        private const val file_name="mvp_kotlin_file"

        private val prefs:SharedPreferences by lazy {
            MyApplication.context.getSharedPreferences(file_name, Context.MODE_PRIVATE)
        }

        /**
         * 删除全部数据
         */
        fun clearPreference(){
            prefs.edit().clear().apply()
        }

        /**
         * 根据key删除存储数据
         */
        fun clearPreference(key:String){
            prefs.edit().remove(key).apply()
        }
    }

    operator fun getValue(thisRef:Any?,property: KProperty<*>):T{
        return getSharedPreferences(name,default)
    }

    operator fun setValue(thisRef: Any?,property: KProperty<*>,value: T){
        putSharedPreference(name,value)
    }

    private fun putSharedPreference(name:String,value:T)=with(prefs.edit()){
        when(value){
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> putString(name,serialize(value))
        }.apply()
    }

    private fun getSharedPreferences(name:String,default:T):T=with(prefs){
        val res:Any=when(default){
            is Long->getLong(name,default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else ->  deSerialization(getString(name,serialize(default)))
        }
        return res as T
    }

    /**
     * 序列化对象
     */
    @Throws(IOException::class)
    private fun<A> serialize(obj:A):String{
        val byteArrayOutputStream=ByteArrayOutputStream()
        val objectOutputStream=ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(obj)
        var serStr=byteArrayOutputStream.toString("ISO-8859-1")
        serStr=java.net.URLEncoder.encode(serStr,"UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr

    }

    /**
     * 反序列化对象
     */
    @Throws(IOException::class)
    private fun<A> deSerialization(str: String): A {
        val redStr=java.net.URLDecoder.decode(str,"UTF-8")
        val byteArrayInputStream=ByteArrayInputStream(redStr.toByteArray(charset("ISO-8859-1")))
        val objecyInputStream=ObjectInputStream(byteArrayInputStream)
        val obj=objecyInputStream.readObject() as A
        objecyInputStream.close()
        byteArrayInputStream.close()
        return obj
    }

    /**
     * 查询某个key是否已经存在
     */
    fun contains(key:String):Boolean{
        return prefs.contains(key)
    }

    /**
     * 返回所有的键值对
     */
    fun getAll():Map<String,*>{
        return prefs.all
    }


}