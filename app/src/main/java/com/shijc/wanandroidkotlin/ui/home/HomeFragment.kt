package com.shijc.wanandroidkotlin.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.ui.home.adapter.HomeAdapter
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.home.bean.BannerModel
import com.shijc.wanandroidkotlin.ui.home.mvp.HomeContract
import com.shijc.wanandroidkotlin.ui.home.mvp.HomePresenter
import com.shijc.wanandroidkotlin.utils.UIhelper


/**
 * @Package com.shijc.wanandroidkotlin.ui.home
 * @Description:
 * @author shijiacheng
 * @date 2019/2/13 下午 2:04
 * @version V1.0
 */
class HomeFragment : Fragment() {
    private lateinit var recyclerView:XRecyclerView

    private lateinit var presenter:HomePresenter
    private lateinit var adapter:HomeAdapter

    private var mDatas = ArrayList<ArticleModel>()

    private var page = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        presenter = HomePresenter(requireContext())
        presenter.attachView(mView)



        initView(view)



        return view
    }

    private fun initView(view:View){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter(context!!,mDatas)
        adapter.listener = object :HomeAdapter.ClickListener{
            override fun onItemClick(item: ArticleModel, position: Int, view: View) {
                UIhelper.openWebView(requireContext(),item.link)
            }
        }
        recyclerView.adapter = adapter
        recyclerView.setLoadingListener(object :XRecyclerView.LoadingListener{
            override fun onRefresh() {
                page = 0
                presenter.getData(page)
            }

            override fun onLoadMore() {
                page++
                presenter.getData(page)
            }

        })

        recyclerView.refresh()


    }


    private var mView = object :HomeContract.View{
        override fun onGetBannerResult(data: BannerModel) {
            adapter.setBanner(data.data)
        }

        override fun onRefresh(datas: List<ArticleModel>) {
            recyclerView.refreshComplete()
            recyclerView.loadMoreComplete()

            mDatas.clear()
            mDatas.addAll(datas)
            adapter.notifyDataSetChanged()

            presenter.getBanner()
        }

        override fun onLoad(datas: List<ArticleModel>) {
            recyclerView.refreshComplete()
            recyclerView.loadMoreComplete()

            mDatas.addAll(datas)
            adapter.notifyDataSetChanged()
        }


        override fun error(code: Int, msg: String) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }

        override fun failure(msg: String) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
        }


    }
}