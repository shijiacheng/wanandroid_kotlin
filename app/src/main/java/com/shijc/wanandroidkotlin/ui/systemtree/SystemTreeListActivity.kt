package com.shijc.wanandroidkotlin.ui.systemtree

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.systemtree.bean.SystemTreeTitleModel

class SystemTreeListActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private var titles = ArrayList<SystemTreeTitleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_tree_list)
        var bundle = intent.extras
        if (bundle!=null){
            titles = bundle.getSerializable("treeData") as ArrayList<SystemTreeTitleModel>
        }
        initView()
    }

    private fun initView() {
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
}
