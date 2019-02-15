package com.shijc.wanandroidkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.shijc.wanandroidkotlin.common.adapter.FragmentAdapter
import com.shijc.wanandroidkotlin.ui.home.HomeFragment
import com.shijc.wanandroidkotlin.utils.BottomNavigationViewHelper
import com.shijc.wanandroidkotlin.widget.NoScrollViewPager
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: NoScrollViewPager
    private lateinit var navigation:BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        viewPager = findViewById(R.id.view_pager)
        navigation = findViewById(R.id.bottom_navigation)

        var mFragments = ArrayList<Fragment>()
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())
        mFragments.add(HomeFragment())

        var fragmentAdapter = FragmentAdapter(supportFragmentManager,mFragments)
        viewPager.adapter = fragmentAdapter
        viewPager.addOnPageChangeListener(pageChangeListener)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation)
    }


    private val pageChangeListener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> navigation.setSelectedItemId(R.id.bottom_navigation_home)
                1 -> navigation.setSelectedItemId(R.id.bottom_navigation_systemtree)
                2 -> navigation.setSelectedItemId(R.id.bottom_navigation_wxarticle)
                3 -> navigation.setSelectedItemId(R.id.bottom_navigation_navi)
                4 -> navigation.setSelectedItemId(R.id.bottom_navigation_project)
            }
        }
    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.bottom_navigation_home->{
                    viewPager.currentItem = 0
                    return true
                }
                R.id.bottom_navigation_systemtree->{
                    viewPager.currentItem = 1
                    return true
                }
                R.id.bottom_navigation_wxarticle->{
                    viewPager.setCurrentItem(2)
                    return true
                }
                R.id.bottom_navigation_navi->{
                    viewPager.setCurrentItem(3)
                    return true
                }
                R.id.bottom_navigation_project->{
                    viewPager.setCurrentItem(4)
                    return true
                }
            }
            return false
        }
    }

}
