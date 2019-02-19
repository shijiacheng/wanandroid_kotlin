package com.shijc.wanandroidkotlin.ui.systemtree.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:46
 * @version V1.0
 */
class SystemTreePresenter:SystemTreeContract.Presenter {
    override fun getData() {
        val client = HttpClient.Builder()
            .url(Apis.SYSTEM_TREE)
            .bodyType(DataType.JSON_OBJECT, SystemTreeResult::class.java)
            .build()
        client.get(object : OnResultListener<SystemTreeResult>() {
            override fun onSuccess(result: SystemTreeResult?) {
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

    private var mView:SystemTreeContract.View ?= null
    override fun attachView(view: IView) {
        mView = view as SystemTreeContract.View
    }

    override fun detachView() {
    }
}