package com.shijc.wanandroidkotlin.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.shijc.wanandroidkotlin.ui.WebviewActivity

/**
 * @Package com.shijc.wanandroidkotlin.utils
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 下午 2:30
 * @version V1.0
 */
class UIhelper {
    companion object {
        fun openWebView(context: Context,url:String){
            var intent = Intent(context,WebviewActivity::class.java)
            var bundle = Bundle()
            bundle.putString("url",url)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}