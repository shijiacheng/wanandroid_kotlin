package com.shijc.wanandroidkotlin.ui.navi.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.navi.bean.NaviResult
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:46
 * @version V1.0
 */
class NaviPresenter:NaviContract.Presenter {
    override fun getData() {
        val client = HttpClient.Builder()
            .url(Apis.NAVI_LIST)
            .bodyType(DataType.JSON_OBJECT, NaviResult::class.java)
            .build()
        client.get(object : OnResultListener<NaviResult>() {
            override fun onSuccess(result: NaviResult?) {
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

    private var mView:NaviContract.View ?= null
    override fun attachView(view: IView) {
        mView = view as NaviContract.View
    }

    override fun detachView() {
    }
}