package com.shijc.wanandroidkotlin.common.mvp

/**
 * @Package com.shijc.wanandroidkotlin.common.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 下午 8:03
 * @version V1.0
 */
interface IPresenter {
    //在Activity创建时提供一个view对象（view即是Activity）
    fun attachView(view: IView)
    //在Activity销毁时清除view对象，防止内存泄露
    fun detachView()
}