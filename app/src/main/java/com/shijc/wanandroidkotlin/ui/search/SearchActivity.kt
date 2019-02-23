package com.shijc.wanandroidkotlin.ui.search

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.search.adapter.SearchAdapter
import com.shijc.wanandroidkotlin.ui.search.bean.SearchHotKeyResult
import com.shijc.wanandroidkotlin.ui.search.mvp.SearchContract
import com.shijc.wanandroidkotlin.ui.search.mvp.SearchPresenter
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.widget.Toast
import com.shijc.wanandroidkotlin.utils.SoftKeyboardUtils


class SearchActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: XRecyclerView
    private lateinit var flexContainer: FlexboxLayout
    private lateinit var llHotKey: LinearLayout
    private lateinit var llResult: LinearLayout

    private lateinit var presenter: SearchPresenter
    private lateinit var adapter: SearchAdapter

    private var page:Int = 0
    private var key:String ?= null
//    private var searchKeys = ArrayList<SystemTreeResult.Data>()
    private var mDatas = ArrayList<ArticleModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        presenter = SearchPresenter()
        presenter.attachView(mView)
        initView()
        presenter.getSearchHotKey()

    }

    private lateinit var searchView:SearchView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        val item = menu!!.findItem(R.id.action_search)
        searchView = item.actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setOnCloseListener {
            showHotKey()
            key = null
            false
        }

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                key = query
                showSearchResult()
                recyclerView.refresh()
                SoftKeyboardUtils.hide(this@SearchActivity)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        } )
        return super.onCreateOptionsMenu(menu)
    }

    private fun initView(){
        toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)
        llHotKey = findViewById(R.id.ll_hotkey)
        llResult = findViewById(R.id.ll_result)
        flexContainer = findViewById(R.id.fbl_container)
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
        adapter = SearchAdapter(this@SearchActivity,mDatas)
        recyclerView.adapter = adapter
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onRefresh() {
                showSearchResult()
                page = 0
                presenter.getData(page,key!!)
            }

            override fun onLoadMore() {
                page ++
                presenter.getData(page,key!!)
            }

        })
        showHotKey()

    }

    private fun showHotKey(){
        llHotKey.visibility = View.VISIBLE
        llResult.visibility = View.GONE
    }

    private fun showSearchResult(){
        llHotKey.visibility = View.GONE
        llResult.visibility = View.VISIBLE
    }

    private var mView = object :SearchContract.View{
        override fun onGetDataResult(datas: List<SearchHotKeyResult.Data>) {
            flexContainer.removeAllViews()
            for(item in datas){
                var textView = TextView(this@SearchActivity)
                textView.text= item.name
                textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray)
                textView.setPadding(10,10,10,10)
                textView.setOnClickListener {
                    key = item.name
                    searchView.setQuery(key,true)
                    searchView.isIconified =false
                    SoftKeyboardUtils.hide(this@SearchActivity)
                }

                var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(10,10,10,10)
                textView.layoutParams = params
                flexContainer.addView(textView)
            }

        }

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
