package com.shijc.wanandroidkotlin.ui.todo.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.todo.bean.TodoResult
import com.shijc.wanandroidkotlin.ui.website.bean.WebsiteReuslt

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:46
 * @version V1.0
 */
class TodoPresenter:TodoContract.Presenter {

    override fun getData(type:Int) {
        val client = HttpClient.Builder()
            .baseUrl(Apis.TODO_LIST)
            .url(type.toString()+"/json")
            .bodyType(DataType.JSON_OBJECT, TodoResult::class.java)
            .build()
        client.get(object : OnResultListener<TodoResult>() {
            override fun onSuccess(result: TodoResult) {
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

    private var mView:TodoContract.View ?= null
    override fun attachView(view: IView) {
        mView = view as TodoContract.View
    }

    override fun detachView() {
    }
}