package com.shijc.wanandroidkotlin.ui.collection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.common.base.SimpleDividerItemDecoration
import com.shijc.wanandroidkotlin.common.mvp.BaseActivity
import com.shijc.wanandroidkotlin.ui.collection.adapter.CollectionAdapter
import com.shijc.wanandroidkotlin.ui.collection.mvp.CollectionContract
import com.shijc.wanandroidkotlin.ui.collection.mvp.CollectionPresenter
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import kotlinx.android.synthetic.main.activity_collection.*

class CollectionActivity : BaseActivity() {

    private lateinit var presenter:CollectionPresenter
    private lateinit var adapter:CollectionAdapter
    private var mDatas = ArrayList<ArticleModel>()
    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        presenter = CollectionPresenter()
        presenter.attachView(mView)

        initData()
    }

    private fun initData(){
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(SimpleDividerItemDecoration(this))
        adapter = CollectionAdapter(this!!,mDatas)
        recycler_view.adapter = adapter
        recycler_view.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onRefresh() {
                page = 0
                presenter.getData(page)
            }

            override fun onLoadMore() {
                page++
                presenter.getData(page)
            }

        })

        recycler_view.refresh()
    }

    private val mView = object :CollectionContract.View{
        override fun onRefresh(datas: List<ArticleModel>) {
            recycler_view.refreshComplete()
            recycler_view.loadMoreComplete()

            mDatas.clear()
            mDatas.addAll(datas)
            adapter.notifyDataSetChanged()
        }

        override fun onLoad(datas: List<ArticleModel>) {
            recycler_view.refreshComplete()
            recycler_view.loadMoreComplete()

            mDatas.addAll(datas)
            adapter.notifyDataSetChanged()
        }

        override fun error(code: Int, msg: String) {
            Toast.makeText(this@CollectionActivity,msg, Toast.LENGTH_SHORT).show()
        }

        override fun failure(msg: String) {
            Toast.makeText(this@CollectionActivity,msg,Toast.LENGTH_SHORT).show()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
