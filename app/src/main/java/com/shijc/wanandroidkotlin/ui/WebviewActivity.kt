package com.shijc.wanandroidkotlin.ui

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.common.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : BaseActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private var url:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val bundle = intent.extras
        if (bundle!=null){
            url = bundle.getString("url")
        }

        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用

        progressBar = findViewById(R.id.progressbar)//进度条

        webView = findViewById(R.id.webview)
        webView!!.webChromeClient = MyWebChromeClient()
        webView!!.webViewClient = MyWebViewClient()

        val webSettings = webView!!.settings
        webSettings.javaScriptEnabled = true//允许使用js

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true

        //不显示webview缩放按钮
        //        webSettings.setDisplayZoomControls(false);

        webView!!.loadUrl(url)//加载url
    }

    inner class MyWebViewClient:WebViewClient(){

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            tool_bar.title = view!!.title
        }
    }

    inner class MyWebChromeClient :WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            progressBar.progress = newProgress
            if (newProgress == 100){
                progressBar.visibility = View.GONE
            }
            super.onProgressChanged(view, newProgress)
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (webView!!.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {//点击返回按钮的时候判断有没有上一页
            webView!!.goBack() // goBack()表示返回webView的上一页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()

        //释放资源
        webView!!.destroy()
    }
}
