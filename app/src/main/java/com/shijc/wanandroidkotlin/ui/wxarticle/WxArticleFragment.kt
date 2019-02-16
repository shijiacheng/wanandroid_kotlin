package com.shijc.wanandroidkotlin.ui.wxarticle

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.shijc.wanandroidkotlin.R
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.ui.wxarticle.bean.WxArticleTitleResult
import com.shijc.wanandroidkotlin.ui.wxarticle.mvp.WxArticleContract
import com.shijc.wanandroidkotlin.ui.wxarticle.mvp.WxArticlePresenter


/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 11:30
 * @version V1.0
 */
class WxArticleFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: MyPagerAdapter

    private lateinit var presenter: WxArticlePresenter

    private var titles = ArrayList<WxArticleTitleResult.Data>()
    private var fragments  = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wx_article, container, false)
        presenter = WxArticlePresenter()
        presenter.attachView(mView)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager)
        adapter = MyPagerAdapter(childFragmentManager!!,fragments,titles)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        presenter.getWxTitle()
    }

    inner class MyPagerAdapter(
        fm: FragmentManager,
        private val fragments: List<Fragment>,
        private val titles: List<WxArticleTitleResult.Data>
    ) : FragmentPagerAdapter(fm) {

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position].name
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

    }


    private var mView = object : WxArticleContract.View{
        override fun onGetWxTitleResult(data: WxArticleTitleResult) {
            titles.clear()
            titles.addAll(data.data)
            for (item in data.data){
                var fragment = WxArticleListFragment()
                var bundle = Bundle()
                bundle.putInt("wxId",item.id)
                fragment.arguments = bundle
                fragments.add(fragment)
            }
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