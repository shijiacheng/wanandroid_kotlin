package com.shijc.wanandroidkotlin.ui.project.mvp

import com.shijc.wanandroidkotlin.common.mvp.IPresenter
import com.shijc.wanandroidkotlin.common.mvp.IView
import com.shijc.wanandroidkotlin.ui.project.bean.ProjectTitleResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.wxarticle.mvp
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 上午 10:06
 * @version V1.0
 */
class ProjectContract {
    interface View:IView{
        fun onGetProjectTitleResult(data: ProjectTitleResult)

    }

    interface Presenter : IPresenter{
        fun getProjectTitle()
    }
}