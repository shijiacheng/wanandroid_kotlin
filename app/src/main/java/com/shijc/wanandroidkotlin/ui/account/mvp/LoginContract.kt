package com.shijc.wanandroidkotlin.ui.account.mvp

import com.shijc.wanandroidkotlin.common.mvp.IPresenter
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.ui.account.bean.LoginReuslt

/**
 * @Package com.shijc.wanandroidkotlin.ui.account.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/25 上午 10:17
 * @version V1.0
 */

class LoginContract{
    interface View:IView{
        fun onLoginResult(result: LoginReuslt)
    }

    interface Presenter:IPresenter{
        fun login(username:String,password:String)
    }
}