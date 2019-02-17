package com.shijc.wanandroidkotlin.common.mvp

/**
 * @Package com.shijc.wanandroidkotlin.common.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 下午 7:57
 * @version V1.0
 */
interface IView {
    fun error(code:Int,msg:String)
    fun failure(msg:String)
}

