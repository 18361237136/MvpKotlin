package com.example.mvpkotlin.mvp.presenter

import com.example.mvpkotlin.base.BasePresenter
import com.example.mvpkotlin.mvp.contract.HomeContract
import com.example.mvpkotlin.mvp.model.HomeModel
import com.example.mvpkotlin.mvp.model.bean.HomeBean
import com.example.mvpkotlin.network.exception.ExceptionHandle

/**
 * Anthor: Zhuangmingzhu
 * Date: 2019/3/7 下午7:52
 * Describe:
 */
class HomePresenter :BasePresenter<HomeContract.View>(),HomeContract.Presenter{

    private var bannerHomeBean:HomeBean?=null

    private var nextPageUrl:String?=null

    private val homeMode:HomeModel by lazy {
        HomeModel()
    }

    /**
     * 获取首页精选数据banner加一页数据
     */
    override fun requestHomeData(num: Int) {
        //检测是否绑定View
        checkViewAttached()
        mRootView?.showLoading()
        val disposable=homeMode.requestHomeData(num)
                .flatMap({homrBean->
                    //过滤掉Banner2(包含广告等不需要的Type)具体看接口分析
                    val bannerItemList=homrBean.issueList[0].itemList

                    bannerItemList.filter {item->
                        item.type=="banner2"||item.type=="horizontalScrollCard"
                    }.forEach { item ->
                        bannerItemList.remove(item)
                    }

                    bannerHomeBean=homrBean//记录第一页是当做banner数据

                    homeMode.loadMoreData(homrBean.nextPageUrl)
                })
                .subscribe({homeBean->
                    mRootView?.apply {
                        dismissLoading()

                        nextPageUrl=homeBean.nextPageUrl

                        val newBannerItemList=homeBean.issueList[0].itemList

                        newBannerItemList.filter {item->
                            item.type=="banner2"||item.type=="horizontalScrollCard"
                        }.forEach {item->
                            newBannerItemList.remove(item)
                        }

                        //重新复制Banner长度
                        bannerHomeBean!!.issueList[0].count=bannerHomeBean!!.issueList[0].itemList.size

                        //复制过滤后的数据+banner数据
                        bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)

                        setHomeData(bannerHomeBean!!)
                    }
                },{t->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {
        val disposable=nextPageUrl?.let {
            homeMode.loadMoreData(it)
                    .subscribe({homeBean->
                        mRootView?.apply {
                            //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                            val newItemList = homeBean.issueList[0].itemList

                            newItemList.filter { item ->
                                item.type=="banner2"||item.type=="horizontalScrollCard"
                            }.forEach{ item ->
                                //移除 item
                                newItemList.remove(item)
                            }

                            nextPageUrl = homeBean.nextPageUrl
                            setMoreData(newItemList)
                        }

                    }, { t ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                        }
                    })
        }

        if(disposable!=null){
            addSubscription(disposable)
        }
    }
}