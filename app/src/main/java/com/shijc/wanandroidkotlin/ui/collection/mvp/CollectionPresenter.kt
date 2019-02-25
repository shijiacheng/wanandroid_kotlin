package com.shijc.wanandroidkotlin.ui.collection.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleResult
import com.shijc.wanandroidkotlin.ui.search.bean.SearchHotKeyResult
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:46
 * @version V1.0
 */
class CollectionPresenter:CollectionContract.Presenter {

    override fun getData(page: Int) {
        val client = HttpClient.Builder()
            .baseUrl(Apis.COLLECTION_LIST)
            .url(page.toString()+"/json")
            .bodyType(DataType.JSON_OBJECT, ArticleResult::class.java)
            .build()
        client.get(object : OnResultListener<ArticleResult>() {
            override fun onSuccess(result: ArticleResult) {
                if (page == 0){
                    mView?.onRefresh(result.data.datas)
                }else{
                    mView?.onLoad(result.data.datas)
                }
            }
            override fun onError(code: Int, message: String) {
                mView?.error(code,message)
            }
            override fun onFailure(message: String) {
                mView?.failure(message)
            }
        })
    }

    private var mView:CollectionContract.View ?= null
    override fun attachView(view: IView) {
        mView = view as CollectionContract.View
    }

    override fun detachView() {
    }
}