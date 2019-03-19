package com.example.mvpkotlin.view.recyclerview

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/9 下午3:44
 * Describe:
 */
class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    //用于缓存已好的界面
    private var mView:SparseArray<View>?=null

    init {
        mView= SparseArray()
    }

    fun<T:View> getView(viewId:Int):T{
        //对已有的view做缓存
        var view:View?=mView?.get(viewId)
        //使用缓存的方式减少findViewByIs的次数
        if(view==null){
            view=itemView.findViewById(viewId)
            mView?.put(viewId,view)
        }
        return view as T
    }

    fun<T:ViewGroup> getViewGroup(viewId:Int):T{
        //对已有的view做缓存
        var view:View?=mView?.get(viewId)
        if(view==null){
            view=itemView.findViewById(viewId)
            mView?.put(viewId,view)
        }
        return view as T
    }

    fun setText(viewId:Int,text:CharSequence):ViewHolder{
        val view=getView<TextView>(viewId)
        view.text="" + text
        return this
    }

    fun setHintText(viewId:Int,text:CharSequence):ViewHolder{
        val view=getView<TextView>(viewId)
        view.hint=""+text
        return this
    }

    /**
     * 设置本地图片
     */
    fun setImageResource(viewId:Int,resId:Int):ViewHolder{
        val iv=getView<ImageView>(viewId)
        iv.setImageResource(resId)
        return this
    }

    fun setImagePath(viewId:Int,imageLoader:HolderImageLoader):ViewHolder{
        val iv=getView<ImageView>(viewId)
        imageLoader.loadImage(iv,imageLoader.path)
        return this
    }

    abstract class HolderImageLoader(val path:String){
        /**
         * 需要去复写这个方法加载图片
         */
        abstract fun loadImage(iv:ImageView,path: String)

    }

    /**
     * 设置view的visibility
     */
    fun setViewVisibility(viewId:Int,visibility:Int):ViewHolder{
        getView<View>(viewId).visibility=visibility
        return this
    }

    /**
     * 设置点击条目
     */
    fun setOnItemClickListener(listener:View.OnClickListener){
        itemView.setOnClickListener(listener)
    }

    /**
     * 设置条目长按事件
     */
    fun setOnItemLongClickListener(listener:View.OnLongClickListener){
        itemView.setOnLongClickListener(listener)
    }

}