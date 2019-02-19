package com.shijc.wanandroidkotlin.ui.systemtree.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 上午 10:29
 * @version V1.0
 */
class SystemTreeListPresenter:SystemTreeListContract.Presenter {
    private var mView:SystemTreeListContract.View ?= null

    override fun getData(page: Int, id: Int) {
        val client = HttpClient.Builder()
            .baseUrl(Apis.SYSTEM_TREE_CONTENT)
            .url(page.toString()+"/json?cid="+id.toString())
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

    override fun attachView(view: IView) {
        mView = view as SystemTreeListContract.View
    }

    override fun detachView() {
    }
}