package com.shijc.wanandroidkotlin.ui.systemtree.mvp

import com.shijc.wanandroidkotlin.common.mvp.IPresenter
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:42
 * @version V1.0
 */
class SystemTreeContract{
    interface View:IView{
        fun onGetDataResult(datas: List<SystemTreeResult.Data>)
    }

    interface Presenter:IPresenter{
        fun getData()
    }
}