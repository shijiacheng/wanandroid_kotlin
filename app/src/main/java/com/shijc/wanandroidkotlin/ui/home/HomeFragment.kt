package com.shijc.wanandroidkotlin.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shijc.wanandroidkotlin.R

/**
 * @Package com.shijc.wanandroidkotlin.ui.home
 * @Description:
 * @author shijiacheng
 * @date 2019/2/13 下午 2:04
 * @version V1.0
 */
class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //View view = inflater.inflate(R.layout.fragment_bookcase, container, false);
        return view
    }

    private fun initView(){
        var datas = ArrayList<String>()
        datas.add("12")
        datas.add("12")
        datas.add("12")
        datas.add("12")
        datas.add("12")
        datas.add("12")
        datas.add("12")
    }
}