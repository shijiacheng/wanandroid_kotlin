package com.shijc.wanandroidkotlin.ui.home.mvp

import com.shijc.wanandroidkotlin.common.mvp.IPresenter
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.home.bean.BannerModel

/**
 * @Package com.shijc.wanandroidkotlin.ui.home.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 上午 9:01
 * @version V1.0
 */
class HomeContract {

    interface View:IView{
        fun onRefresh(datas: List<ArticleModel>)
        fun onLoad(datas: List<ArticleModel>)
        fun onGetBannerResult(data: BannerModel)

    }

    interface Presenter:IPresenter{
        fun getData(page:Int)
        fun getBanner()

    }

}