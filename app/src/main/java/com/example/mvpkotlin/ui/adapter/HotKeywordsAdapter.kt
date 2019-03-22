package com.example.mvpkotlin.ui.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.mvpkotlin.R
import com.example.mvpkotlin.view.recyclerview.ViewHolder
import com.example.mvpkotlin.view.recyclerview.adapter.CommonAdapter
import com.example.mvpkotlin.view.recyclerview.adapter.OnItemClickListener
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/22 上午10:10
 * Describe:Tag标签布局的Adapter
 */
class HotKeywordsAdapter(mContext: Context,mList:ArrayList<String>,layoutId:Int):CommonAdapter<String>(mContext,mList,layoutId){

    //kotlin的函数可以作为参数，写callback的时候，可以不用interface了
    private var onTagItemClick:((tag:String)->Unit)?=null

    fun setOnTagItemClickListener(onTagItemClickListener: (tag:String)->Unit){
        this.onTagItemClick=onTagItemClickListener
    }

    override fun bindData(holder: ViewHolder, data: String, position: Int) {
        holder.setText(R.id.tv_title,data)

        val params=holder.getView<TextView>(R.id.tv_title).layoutParams
        if(params is FlexboxLayoutManager.LayoutParams){
            params.flexGrow=1.0f
        }

        holder.setOnItemClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onTagItemClick?.invoke(data)
            }
        })
    }

}