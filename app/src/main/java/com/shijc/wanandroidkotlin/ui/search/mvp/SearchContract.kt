package com.shijc.wanandroidkotlin.ui.search.mvp

import com.shijc.wanandroidkotlin.common.mvp.IPresenter
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.search.bean.SearchHotKeyResult
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:42
 * @version V1.0
 */
class SearchContract{
    interface View:IView{
        fun onGetDataResult(datas: List<SearchHotKeyResult.Data>)
        fun onRefresh(datas: List<ArticleModel>)
        fun onLoad(datas: List<ArticleModel>)
    }

    interface Presenter:IPresenter{
        fun getSearchHotKey()
        fun getData(page:Int,key:String)
    }
}