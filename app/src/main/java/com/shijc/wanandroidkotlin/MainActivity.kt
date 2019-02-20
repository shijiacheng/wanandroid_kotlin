package com.shijc.wanandroidkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.shijc.wanandroidkotlin.ui.home.HomeFragment
import com.shijc.wanandroidkotlin.ui.navi.NaviFragment
import com.shijc.wanandroidkotlin.ui.project.ProjectFragment
import com.shijc.wanandroidkotlin.ui.systemtree.SystemTreeFragment
import com.shijc.wanandroidkotlin.ui.wxarticle.WxArticleFragment
import com.shijc.wanandroidkotlin.utils.BottomNavigationViewHelper
import com.shijc.wanandroidkotlin.utils.Utils
import android.widget.Toast
import android.support.v7.widget.Toolbar
import android.view.View
import com.shijc.wanandroidkotlin.ui.search.SearchActivity


class MainActivity : AppCompatActivity() {

    private lateinit var navigation:BottomNavigationView
    private lateinit var toolbar:Toolbar

    private lateinit var homeFragment:Fragment
    private var systemTreeFragment:Fragment? =null
    private  var wxArticleFragment:Fragment? =null
    private  var naviFragment:Fragment? =null
    private  var projectFragment:Fragment? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.init(this)
        initView()

        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> {
                    startActivity(Intent(this@MainActivity,SearchActivity::class.java))
                }
            }
            true
        }
    }

    private fun initView(){
        toolbar = findViewById(R.id.tool_bar)
        navigation = findViewById(R.id.bottom_navigation)
        var fragmentManager = supportFragmentManager
        var transaction = fragmentManager.beginTransaction()
        homeFragment = HomeFragment()
        transaction.replace(R.id.view_container, homeFragment)
        mContent = homeFragment
        transaction.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation)
    }

    lateinit var mContent:Fragment

    fun switchContent(to: Fragment) {
        if (mContent !== to) {
            val transaction = supportFragmentManager
                .beginTransaction()
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.view_container, to).commit() // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit() // 隐藏当前的fragment，显示下一个
            }
            mContent = to
        }
    }


    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.bottom_navigation_home->{
                    toolbar.visibility = View.VISIBLE
                    if (homeFragment == null) {
                        homeFragment = HomeFragment()
                    }
                    switchContent(homeFragment)
                    return true
                }
                R.id.bottom_navigation_systemtree->{
                    toolbar.visibility = View.VISIBLE
                    if (systemTreeFragment == null) {
                        systemTreeFragment = SystemTreeFragment()
                    }
                    switchContent(systemTreeFragment!!)
                    return true
                }
                R.id.bottom_navigation_wxarticle->{
                    toolbar.visibility = View.GONE
                    if (wxArticleFragment == null) {
                        wxArticleFragment = WxArticleFragment()
                    }
                    switchContent(wxArticleFragment!!)
                    return true
                }
                R.id.bottom_navigation_navi->{
                    toolbar.visibility = View.VISIBLE
                    if (naviFragment == null) {
                        naviFragment = NaviFragment()
                    }
                    switchContent(naviFragment!!)
                    return true
                }
                R.id.bottom_navigation_project->{
                    toolbar.visibility = View.GONE
                    if (projectFragment == null) {
                        projectFragment = ProjectFragment()
                    }
                    switchContent(projectFragment!!)
                    return true
                }
            }
            return false
        }
    }


    override fun onBackPressed() {
        val i = Intent(Intent.ACTION_MAIN)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        i.addCategory(Intent.CATEGORY_HOME)
        startActivity(i)
    }
}
