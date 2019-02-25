package com.shijc.wanandroidkotlin.ui.website.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.website.bean.WebsiteReuslt

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:46
 * @version V1.0
 */
class WebsitePresenter:WebsiteContract.Presenter {

    override fun getData() {
        val client = HttpClient.Builder()
            .url(Apis.WEBSITE_COLLECTION_LIST)
            .bodyType(DataType.JSON_OBJECT, WebsiteReuslt::class.java)
            .build()
        client.get(object : OnResultListener<WebsiteReuslt>() {
            override fun onSuccess(result: WebsiteReuslt) {
                mView?.onGetReuslt(result.data)
            }
            override fun onError(code: Int, message: String) {
                mView?.error(code,message)
            }
            override fun onFailure(message: String) {
                mView?.failure(message)
            }
        })
    }

    private var mView:WebsiteContract.View ?= null
    override fun attachView(view: IView) {
        mView = view as WebsiteContract.View
    }

    override fun detachView() {
    }
}