package com.shijc.wanandroidkotlin.ui.website.mvp

import com.shijc.wanandroidkotlin.common.mvp.IPresenter
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.ui.website.bean.WebsiteReuslt

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:42
 * @version V1.0
 */
class WebsiteContract{
    interface View:IView{
        fun onGetReuslt(datas: List<WebsiteReuslt.Data>)
    }

    interface Presenter:IPresenter{
        fun getData()
    }
}