package com.shijc.wanandroidkotlin.ui.search.mvp

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
class SearchPresenter:SearchContract.Presenter {
    override fun getSearchHotKey() {
        val client = HttpClient.Builder()
            .url(Apis.SEARCH_HOT_WORD)
            .bodyType(DataType.JSON_OBJECT, SearchHotKeyResult::class.java)
            .build()
        client.get(object : OnResultListener<SearchHotKeyResult>() {
            override fun onSuccess(result: SearchHotKeyResult?) {
                mView?.onGetDataResult(result?.data!!)
            }
            override fun onError(code: Int, message: String) {
                mView?.error(code,message)
            }
            override fun onFailure(message: String) {
                mView?.failure(message)
            }
        })
    }


    override fun getData(page: Int,key:String) {
        val client = HttpClient.Builder()
            .baseUrl(Apis.SEARCH_RESULT)
            .url(page.toString()+"/json").params("k",key)
            .bodyType(DataType.JSON_OBJECT, ArticleResult::class.java)
            .build()
        client.post(object : OnResultListener<ArticleResult>() {
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

    private var mView:SearchContract.View ?= null
    override fun attachView(view: IView) {
        mView = view as SearchContract.View
    }

    override fun detachView() {
    }
}