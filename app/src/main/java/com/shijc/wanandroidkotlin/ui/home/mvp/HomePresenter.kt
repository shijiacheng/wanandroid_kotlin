package com.shijc.wanandroidkotlin.ui.home.mvp

import android.content.Context
import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleResult
import com.shijc.wanandroidkotlin.ui.home.bean.BannerModel


/**
 * @Package com.shijc.wanandroidkotlin.ui.home.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 上午 9:02
 * @version V1.0
 */
class HomePresenter(private var context: Context) : HomeContract.Presenter{
    override fun getBanner() {
        val client = HttpClient.Builder()
            .url(Apis.HOME_BANNER)
            .bodyType(DataType.JSON_OBJECT, BannerModel::class.java)
            .build()
        client.get(object : OnResultListener<BannerModel>() {
            override fun onSuccess(result: BannerModel?) {
                mView?.onGetBannerResult(result!!)
            }
            override fun onError(code: Int, message: String) {
                mView?.error(code,message)
            }
            override fun onFailure(message: String) {
                mView?.failure(message)
            }
        })
    }

    override fun getData(page: Int) {
        val client = HttpClient.Builder()
            .baseUrl(Apis.HOME_ARTICLE_LIST)
            .url(page.toString()+"/json")
            .bodyType(DataType.JSON_OBJECT, ArticleResult::class.java)
            .build()
        client.get(object : OnResultListener<ArticleResult>() {

            override fun onSuccess(result: ArticleResult) {
                if (page == 0){
                    mView?.onRefresh(result.data.datas)
                }else{
                    mView?.onLoad(result.data.datas)
                }
            }

            override fun onError(code: Int, message: String) {
                mView?.error(code,message)
            }

            override fun onFailure(message: String) {
                mView?.failure(message)
            }
        })
    }

    private var mView: HomeContract.View? = null

    override fun attachView(view: IView) {
        mView = view as HomeContract.View
    }

    override fun detachView() {
    }


}