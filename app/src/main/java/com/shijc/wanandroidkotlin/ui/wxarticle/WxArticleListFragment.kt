package com.shijc.wanandroidkotlin.ui.wxarticle

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.wxarticle.adapter.WxArticleListAdapter
import com.shijc.wanandroidkotlin.ui.wxarticle.mvp.WxArticleListContract
import com.shijc.wanandroidkotlin.ui.wxarticle.mvp.WxArticleListPresenter

/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 11:39
 * @version V1.0
 */
class WxArticleListFragment : Fragment() {
    private lateinit var recyclerView: XRecyclerView
    private var wxId:Int = 0

    private lateinit var presenter:WxArticleListPresenter
    private lateinit var adapter: WxArticleListAdapter

    private var page:Int = 0

    private var mDatas = ArrayList<ArticleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wx_article_list, container, false)
        var bundle = arguments
        if (bundle!=null){
            wxId = bundle.getInt("wxId")
        }
        presenter = WxArticleListPresenter()
        presenter.attachView(mView)
        initView(view)
        return view
    }

    private fun initView(view: View){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = WxArticleListAdapter(context!!,mDatas)
        recyclerView.adapter = adapter
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onRefresh() {
                page = 0
                presenter.getWxArticle(page,wxId)
            }

            override fun onLoadMore() {
                page ++
                presenter.getWxArticle(page,wxId)
            }

        })
        recyclerView.refresh()


    }

    private var mView = object :WxArticleListContract.View{
        override fun onRefresh(datas: List<ArticleModel>) {
            recyclerView.refreshComplete()
            recyclerView.loadMoreComplete()
            mDatas.clear()
            mDatas.addAll(datas)
            adapter.notifyDataSetChanged()
        }

        override fun onLoad(datas: List<ArticleModel>) {
            recyclerView.refreshComplete()
            recyclerView.loadMoreComplete()
            mDatas.addAll(datas)
            adapter.notifyDataSetChanged()
        }

        override fun error(code: Int, msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun failure(msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}