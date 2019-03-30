package com.shijc.wanandroidkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.shijc.wanandroidkotlin.ui.home.HomeFragment
import com.shijc.wanandroidkotlin.ui.navi.NaviFragment
import com.shijc.wanandroidkotlin.ui.project.ProjectFragment
import com.shijc.wanandroidkotlin.ui.systemtree.SystemTreeFragment
import com.shijc.wanandroidkotlin.ui.wxarticle.WxArticleFragment
import com.shijc.wanandroidkotlin.utils.BottomNavigationViewHelper
import com.shijc.wanandroidkotlin.utils.Utils
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.shijc.wanandroidkotlin.common.base.MessageEvent
import com.shijc.wanandroidkotlin.ui.account.LoginActivity
import com.shijc.wanandroidkotlin.ui.collection.CollectionActivity
import com.shijc.wanandroidkotlin.ui.search.SearchActivity
import com.shijc.wanandroidkotlin.ui.todo.TodoActivity
import com.shijc.wanandroidkotlin.ui.website.WebsiteActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.draw_header_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import com.shijc.wanandroidkotlin.api.Apis
import com.shijc.wanandroidkotlin.common.base.Preference
import com.shijc.wanandroidkotlin.common.mvp.BaseActivity
import com.shijc.wanandroidkotlin.http.DataType
import com.shijc.wanandroidkotlin.http.HttpClient
import com.shijc.wanandroidkotlin.http.OnResultListener
import com.shijc.wanandroidkotlin.ui.account.bean.LoginReuslt


class MainActivity : BaseActivity() {

    private lateinit var navigation:BottomNavigationView
    private lateinit var toolbar:Toolbar
    private lateinit var llUser:ViewGroup
    private lateinit var tvUser:TextView
    private lateinit var ivAvatar:ImageView
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var homeFragment:Fragment
    private var systemTreeFragment:Fragment? =null
    private  var wxArticleFragment:Fragment? =null
    private  var naviFragment:Fragment? =null
    private  var projectFragment:Fragment? =null

    private var userNamePref: String by Preference(this,"userName", "")
    private var passWordPref: String by Preference(this,"passWord", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EventBus.getDefault().register(this)
        Utils.init(this)
        initView()
        initData()

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
        drawerLayout = findViewById(R.id.id_drawer_layout)
        llUser = findViewById(R.id.ll_user)
        tvUser = findViewById(R.id.tv_username)
        toolbar = findViewById(R.id.tool_bar)
        ivAvatar = findViewById(R.id.iv_avatar)
        navigation = findViewById(R.id.bottom_navigation)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用

        var fragmentManager = supportFragmentManager
        var transaction = fragmentManager.beginTransaction()
        homeFragment = HomeFragment()
        transaction.replace(R.id.view_container, homeFragment)
        mContent = homeFragment
        transaction.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        BottomNavigationViewHelper.disableShiftMode(navigation)
    }


    private fun initData(){
        if ("" != userNamePref){
            tvUser.text = userNamePref
            autoLogin(userNamePref,passWordPref)
        }else{
            llUser.setOnClickListener{
                startActivity(Intent(this@MainActivity,LoginActivity::class.java))
            }
        }
        ll_collection.setOnClickListener { startActivity(Intent(this@MainActivity,CollectionActivity::class.java)) }
        ll_website.setOnClickListener { startActivity(Intent(this@MainActivity, WebsiteActivity::class.java)) }
        ll_todo.setOnClickListener { startActivity(Intent(this@MainActivity, TodoActivity::class.java)) }
    }

    lateinit var mContent:Fragment

    fun switchContent(to: Fragment) {
        if (mContent !== to) {
            val transaction = supportFragmentManager
                .beginTransaction()
            if (!to.isAdded) { // 先判断是否被add过
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
                    toolbar.title = "首页"
                    if (homeFragment == null) {
                        homeFragment = HomeFragment()
                    }
                    switchContent(homeFragment)
                    return true
                }
                R.id.bottom_navigation_systemtree->{
                    toolbar.visibility = View.VISIBLE
                    toolbar.title = "体系"
                    if (systemTreeFragment == null) {
                        systemTreeFragment = SystemTreeFragment()
                    }
                    switchContent(systemTreeFragment!!)
                    return true
                }
                R.id.bottom_navigation_wxarticle->{
                    toolbar.visibility = View.GONE
                    toolbar.title = "公众号"
                    if (wxArticleFragment == null) {
                        wxArticleFragment = WxArticleFragment()
                    }
                    switchContent(wxArticleFragment!!)
                    return true
                }
                R.id.bottom_navigation_navi->{
                    toolbar.visibility = View.VISIBLE
                    toolbar.title = "导航"
                    if (naviFragment == null) {
                        naviFragment = NaviFragment()
                    }
                    switchContent(naviFragment!!)
                    return true
                }
                R.id.bottom_navigation_project->{
                    toolbar.visibility = View.GONE
                    toolbar.title = "项目"
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        when(event.msgWhat){
            1->{
                tvUser.text = event.obj.toString()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> drawerLayout.openDrawer(Gravity.LEFT)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun autoLogin(username: String, password: String) {
        val client = HttpClient.Builder()
            .url(Apis.USER_LOGIN)
            .params("username",username)
            .params("password",password)
            .bodyType(DataType.JSON_OBJECT, LoginReuslt::class.java)
            .build()
        client.post(object : OnResultListener<LoginReuslt>() {
            override fun onSuccess(result: LoginReuslt?) {
                EventBus.getDefault().post(MessageEvent(1,username))
            }
            override fun onError(code: Int, message: String) {

            }
            override fun onFailure(message: String) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
