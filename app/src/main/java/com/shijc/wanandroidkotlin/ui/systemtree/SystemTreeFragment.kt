package com.shijc.wanandroidkotlin.ui.systemtree

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.systemtree.adapter.SystemTreeAdapter
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeResult
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeTitleModel
import com.shijc.wanandroidkotlin.ui.systemtree.mvp.SystemTreeContract
import com.shijc.wanandroidkotlin.ui.systemtree.mvp.SystemTreePresenter
import java.io.Serializable

/**
 * @Package com.shijc.wanandroidkotlin.ui.home
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 10:29
 * @version V1.0
 */
class SystemTreeFragment  : Fragment() {
    private lateinit var recyclerView: XRecyclerView

    private lateinit var presenter: SystemTreePresenter
    private lateinit var adapter: SystemTreeAdapter

    private var datas = ArrayList<SystemTreeResult.Data>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_system_tree, container, false)
        presenter = SystemTreePresenter()
        presenter.attachView(mView)
        initView(view)
        return view
    }

    private fun initView(view: View){


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SystemTreeAdapter(context!!,datas)
        recyclerView.adapter = adapter
        adapter.listener = object :SystemTreeAdapter.ClickListener{
            override fun onItemClick(item: SystemTreeResult.Data, position: Int, view: View) {
                val intent = Intent(context,SystemTreeListActivity::class.java)
                var bundle = Bundle()
                var list = ArrayList<SystemTreeTitleModel>()
                for (i in item.children){
                    list.add(SystemTreeTitleModel(i.id,i.name))
                }
                bundle.putSerializable("treeData",list)
                intent.putExtras(bundle)
                startActivity(intent)
            }

        }


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

    private var mView = object :SystemTreeContract.View{
        override fun onGetDataResult(mdatas: List<SystemTreeResult.Data>) {
            datas.clear()
            datas.addAll(mdatas)
            adapter.notifyDataSetChanged()
            recyclerView.refreshComplete()
        }

        override fun error(code: Int, msg: String) {
        }

        override fun failure(msg: String) {
        }

    }
}