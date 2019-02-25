package com.shijc.wanandroidkotlin

import android.app.Application
import kotlin.properties.Delegates

/**
 * @Package com.shijc.wanandroidkotlin
 * @Description:
 * @author shijiacheng
 * @date 2019/2/25 下午 1:48
 * @version V1.0
 */
class AppContext:Application(){
    companion object {
        private var instance:Application ?= null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }




//    companion object {
//        var instance: AppContext by Delegates.notNull()
//    }
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//    }
}