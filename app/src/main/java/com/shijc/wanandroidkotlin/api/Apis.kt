package com.shijc.wanandroidkotlin.api

/**
 * @Package com.shijc.wanandroidkotlin.api
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 上午 9:47
 * @version V1.0
 */
class Apis {
    companion object {
        // 首页banner
        const val HOME_BANNER = "http://www.wanandroid.com/banner/json";
        // 首页文章列表
        const val HOME_ARTICLE_LIST = "http://www.wanandroid.com/article/list/";
        // 知识体系
        const val SYSTEM_TREE = "http://www.wanandroid.com/tree/json";
        // 知识体系详情
        const val SYSTEM_TREE_CONTENT = "http://www.wanandroid.com/article/list/";
        // 公众号名称
        const val WX_LIST = "http://wanandroid.com/wxarticle/chapters/json";
        // 公众号文章
        const val WX_ARTICLE_LIST = "http://wanandroid.com/wxarticle/list/";
        // 导航列表数据
        const val NAVI_LIST = "http://www.wanandroid.com/navi/json";
        // 项目分类
        const val PROJECT_TREE = "http://www.wanandroid.com/project/tree/json";
        // 项目列表
        const val PROJECT_LIST = "http://www.wanandroid.com/project/list/";
        // 搜索热词
        const val SEARCH_HOT_WORD = "http://www.wanandroid.com//hotkey/json";
        // 搜索结果
        const val SEARCH_RESULT = "https://www.wanandroid.com/article/query/";
        // 用户登录
        const val USER_LOGIN = "https://www.wanandroid.com/user/login";
        // 用户注册
        const val USER_REGISTER = "http://www.wanandroid.com/user/register";
        // 收藏列表
        const val COLLECTION_LIST = "http://www.wanandroid.com/lg/collect/list/";

        // 我的收藏-取消收藏
        const val CANCEL_COLLECTION = "http://www.wanandroid.com/lg/uncollect/";

        // 我的收藏-新增收藏
        const val ADD_COLLECTION = "http://www.wanandroid.com/lg/collect/add/json";
        // 网站收藏
        const val WEBSITE_COLLECTION_LIST = "http://www.wanandroid.com/lg/collect/usertools/json";
        // 取消网站收藏
        const val CANCEL_WEBSITE_COLLECTION = "http://www.wanandroid.com/lg/collect/deletetool/json";
        // 新增网站收藏
        const val ADD_WEBSITE_COLLECTION = "http://www.wanandroid.com/lg/collect/addtool/json";
        // 编辑网站收藏
        const val EDIT_WEBSITE_COLLECTION = "http://www.wanandroid.com/lg/collect/updatetool/json";

        // todo列表数据
        const val TODO_LIST = "http://wanandroid.com/lg/todo/list/";
        // 新增todo数据
        const val ADD_TODO = "http://www.wanandroid.com/lg/todo/add/json";
        // 更新todo数据
        const val UPDATE_TODO = "http://www.wanandroid.com/lg/todo/update/";
        // 删除todo数据
        const val DELETE_TODO = "http://www.wanandroid.com/lg/todo/delete/";
        // 仅更新todo完成状态
        const val DONE_TODO = "http://www.wanandroid.com/lg/todo/done/";
    }
}

