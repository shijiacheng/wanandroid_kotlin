package com.shijc.wanandroidkotlin.ui.systemtree.mvp

import com.shijc.wanandroidkotlin.common.mvp.IPresenter
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.home.bean.BannerModel
import com.shijc.wanandroidkotlin.ui.wxarticle.bean.WxArticleTitleResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 上午 10:06
 * @version V1.0
 */
class SystemTreeListContract {
    interface View:IView{
        fun onRefresh(datas: List<ArticleModel>)
        fun onLoad(datas: List<ArticleModel>)

    }

    interface Presenter : IPresenter{
        fun getData(page:Int,id:Int)
    }
}