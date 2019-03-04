package com.shijc.wanandroidkotlin.ui.account.mvp

import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.base.Preference
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.account.bean.LoginReuslt
import com.shijc.wanandroidkotlin.ui.home.bean.BannerModel

/**
 * @Package com.shijc.wanandroidkotlin.ui.account.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/25 上午 10:22
 * @version V1.0
 */

class LoginPresenter:LoginContract.Presenter{
    private var mView:LoginContract.View ?= null

    override fun login(username: String, password: String) {
        val client = HttpClient.Builder()
            .url(Apis.USER_LOGIN)
            .params("username",username)
            .params("password",password)
            .bodyType(DataType.JSON_OBJECT, LoginReuslt::class.java)
            .build()
        client.post(object : OnResultListener<LoginReuslt>() {
            override fun onSuccess(result: LoginReuslt?) {

                mView?.onLoginResult(result!!)
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
        mView = view as LoginContract.View
    }

    override fun detachView() {
    }

}