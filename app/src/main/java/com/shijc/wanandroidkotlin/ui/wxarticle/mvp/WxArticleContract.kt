package com.shijc.wanandroidkotlin.ui.wxarticle.mvp

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
class WxArticleContract {
    interface View:IView{
        fun onGetWxTitleResult(data: WxArticleTitleResult)

    }

    interface Presenter : IPresenter{
        fun getWxTitle()
    }
}