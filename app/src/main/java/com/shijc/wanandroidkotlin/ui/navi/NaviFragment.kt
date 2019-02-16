package com.shijc.wanandroidkotlin.ui.navi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.navi.adapter.NaviAdapter
import com.shijc.wanandroidkotlin.ui.navi.bean.NaviResult
import com.shijc.wanandroidkotlin.ui.navi.mvp.NaviContract
import com.shijc.wanandroidkotlin.ui.navi.mvp.NaviPresenter
import com.shijc.wanandroidkotlin.ui.systemtree.adapter.SystemTreeAdapter
import com.shijc.wanandroidkotlin.ui.systemtree.mvp.SystemTreePresenter

/**
 * @Package com.shijc.wanandroidkotlin.ui.navi
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 下午 1:56
 * @version V1.0
 */
class NaviFragment : Fragment() {
    private lateinit var recyclerView: XRecyclerView

    private lateinit var presenter: NaviPresenter
    private lateinit var adapter: NaviAdapter

    private var mDatas = ArrayList<NaviResult.Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navi, container, false)
        presenter = NaviPresenter()
        presenter.attachView(mView)
        initView(view)
        return view
    }

    private fun initView(view: View){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NaviAdapter(context!!,mDatas)
        recyclerView.adapter = adapter
        recyclerView.setLoadingMoreEnabled(false)
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onRefresh() {
                presenter.getData()
            }

            override fun onLoadMore() {
            }

        })

        recyclerView.refresh()

    }

    private var mView = object :NaviContract.View{
        override fun onGetDataResult(datas: List<NaviResult.Data>) {
            mDatas.clear()
            mDatas.addAll(datas)
            adapter.notifyDataSetChanged()
            recyclerView.refreshComplete()
        }

        override fun error(code: Int, msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun failure(msg: String) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}