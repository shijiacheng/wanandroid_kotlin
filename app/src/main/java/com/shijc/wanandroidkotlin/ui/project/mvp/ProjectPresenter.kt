package com.shijc.wanandroidkotlin.ui.project.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.project.bean.ProjectTitleResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 上午 10:29
 * @version V1.0
 */
class ProjectPresenter:ProjectContract.Presenter {
    private var mView:ProjectContract.View ?= null

    override fun getProjectTitle() {
        val client = HttpClient.Builder()
            .url(Apis.PROJECT_TREE)
            .bodyType(DataType.JSON_OBJECT, ProjectTitleResult::class.java)
            .build()
        client.get(object : OnResultListener<ProjectTitleResult>() {
            override fun onSuccess(result: ProjectTitleResult?) {
                mView?.onGetProjectTitleResult(result!!)
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
        mView = view as ProjectContract.View
    }

    override fun detachView() {
    }
}