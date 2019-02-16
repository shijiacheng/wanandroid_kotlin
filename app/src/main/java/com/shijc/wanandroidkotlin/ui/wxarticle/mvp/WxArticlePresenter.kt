package com.shijc.wanandroidkotlin.ui.wxarticle.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.home.bean.BannerModel
import com.shijc.wanandroidkotlin.ui.wxarticle.bean.WxArticleTitleResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 上午 10:29
 * @version V1.0
 */
class WxArticlePresenter:WxArticleContract.Presenter {
    private var mView:WxArticleContract.View ?= null

    override fun getWxTitle() {
        val client = HttpClient.Builder()
            .url(Apis.WX_LIST)
            .bodyType(DataType.JSON_OBJECT, WxArticleTitleResult::class.java)
            .build()
        client.get(object : OnResultListener<WxArticleTitleResult>() {
            override fun onSuccess(result: WxArticleTitleResult?) {
                mView?.onGetWxTitleResult(result!!)
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
        mView = view as WxArticleContract.View
    }

    override fun detachView() {
    }
}