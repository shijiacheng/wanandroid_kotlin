package com.shijc.wanandroidkotlin.ui.account

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.common.base.Preference
import com.shijc.wanandroidkotlin.ui.account.bean.LoginReuslt
import com.shijc.wanandroidkotlin.ui.account.mvp.LoginContract
import com.shijc.wanandroidkotlin.ui.account.mvp.LoginPresenter

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mPresenter: LoginPresenter;
    private var userNamePref: String by Preference("userName", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter();
        mPresenter.attachView(mView)

        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用

        initListener()
    }

    private fun initListener() {
        tool_bar.setNavigationOnClickListener{ finish() }

        btn_login.setOnClickListener {
            val username = tie_username?.text.toString()
            val password = tie_password?.text.toString()
            mPresenter.login(username,password)
        }
    }

    private val mView = object : LoginContract.View{
        override fun onLoginResult(result: LoginReuslt) {
            if (result.errorCode == 0){
                userNamePref = result.data.username
                finish()
            }else{

            }
        }

        override fun error(code: Int, msg: String) {
        }

        override fun failure(msg: String) {
        }

    }

}
