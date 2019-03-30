package com.shijc.wanandroidkotlin.ui.systemtree

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.common.base.SimpleDividerItemDecoration
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.systemtree.adapter.SystemTreeListAdapter
import com.shijc.wanandroidkotlin.ui.systemtree.mvp.SystemTreeListContract
import com.shijc.wanandroidkotlin.ui.systemtree.mvp.SystemTreeListPresenter
import com.shijc.wanandroidkotlin.utils.UIhelper

/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 11:39
 * @version V1.0
 */
class SystemTreeListFragment  : Fragment() {
    private lateinit var recyclerView: XRecyclerView

    private var systemId:Int = 0

    private lateinit var presenter: SystemTreeListPresenter
    private lateinit var adapter: SystemTreeListAdapter

    private var page:Int = 0

    private var mDatas = ArrayList<ArticleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_system_tree_list, container, false)
        var bundle = arguments
        if (bundle!=null){
            systemId = bundle.getInt("systemId")
        }
        presenter = SystemTreeListPresenter()
        presenter.attachView(mView)
        initView(view)
        return view
    }

    private fun initView(view: View){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(context))
        adapter = SystemTreeListAdapter(context!!,mDatas)
        recyclerView.adapter = adapter
        adapter.listener = object : SystemTreeListAdapter.ClickListener{
            override fun onItemClick(item: ArticleModel, position: Int, view: View) {
                UIhelper.openWebView(requireContext(),item.link)
            }

        }
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onRefresh() {
                page = 0
                presenter.getData(page,systemId)
            }

            override fun onLoadMore() {
                page ++
                presenter.getData(page,systemId)
            }

        })
        recyclerView.refresh()


    }

    private var mView = object : SystemTreeListContract.View{
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