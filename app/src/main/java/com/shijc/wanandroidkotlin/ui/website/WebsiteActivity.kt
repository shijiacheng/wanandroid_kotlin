package com.shijc.wanandroidkotlin.ui.website

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.common.mvp.BaseActivity
import com.shijc.wanandroidkotlin.ui.website.bean.WebsiteReuslt
import com.shijc.wanandroidkotlin.ui.website.mvp.WebsiteContract
import com.shijc.wanandroidkotlin.ui.website.mvp.WebsitePresenter
import com.shijc.wanandroidkotlin.utils.UIhelper
import kotlinx.android.synthetic.main.activity_website.*

class WebsiteActivity : BaseActivity() {
    private lateinit var presenter: WebsitePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website)

        presenter = WebsitePresenter()
        presenter.attachView(mView)

        initView()
        initData()
    }

    private fun initView(){
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用

    }

    private fun initData(){



        presenter.getData()
    }

    private val mView = object:WebsiteContract.View{
        override fun onGetReuslt(datas: List<WebsiteReuslt.Data>) {
            fbl_container.removeAllViews()
            datas.forEach{
                var textView = TextView(this@WebsiteActivity)
                textView.text= it.name
                textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray)
                textView.setPadding(40,15,40,15)

                var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(20,10,20,10)
                textView.layoutParams = params
                val link = it.link
                textView.setOnClickListener {
                    UIhelper.openWebView(this@WebsiteActivity,link)
                }
                fbl_container.addView(textView)
            }
        }

        override fun error(code: Int, msg: String) {
        }

        override fun failure(msg: String) {
        }

    }
}
