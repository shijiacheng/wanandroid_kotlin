package com.shijc.wanandroidkotlin.ui.systemtree

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.common.mvp.BaseActivity
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeTitleModel
import kotlinx.android.synthetic.main.activity_system_tree_list.*

class SystemTreeListActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private var titles = ArrayList<SystemTreeTitleModel>()
    private lateinit var title:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_tree_list)
        var bundle = intent.extras
        if (bundle!=null){
            title = bundle.getString("title")
            titles = bundle.getSerializable("treeData") as ArrayList<SystemTreeTitleModel>
        }
        initView()
    }

    private fun initView() {
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用
        tool_bar.title = title

        var fragments = ArrayList<Fragment>()

        for (item in titles){
            var fragment = SystemTreeListFragment()
            var bundle = Bundle()
            bundle.putInt("systemId",item.id)
            fragment.arguments = bundle
            fragments.add(fragment)
        }

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        val adapter = MyPagerAdapter(supportFragmentManager!!,fragments,titles)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }

    inner class MyPagerAdapter(
        fm: FragmentManager,
        private val fragments: List<Fragment>,
        private val titles: List<SystemTreeTitleModel>
    ) : FragmentPagerAdapter(fm) {

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position].title
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
