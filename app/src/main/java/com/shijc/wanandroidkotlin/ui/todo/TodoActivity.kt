package com.shijc.wanandroidkotlin.ui.todo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatRadioButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.todo.adapter.ChildViewBinder
import com.shijc.wanandroidkotlin.ui.todo.adapter.GroupViewBinder
import com.shijc.wanandroidkotlin.ui.todo.bean.ChildModel
import com.shijc.wanandroidkotlin.ui.todo.bean.GroupModel
import com.shijc.wanandroidkotlin.ui.todo.bean.TodoResult
import com.shijc.wanandroidkotlin.ui.todo.mvp.TodoContract
import com.shijc.wanandroidkotlin.ui.todo.mvp.TodoPresenter
import com.shijc.wanandroidkotlin.utils.CollectionUtils
import com.shijc.wanandroidkotlin.utils.TimeUtils
import com.shijc.wanandroidkotlin.utils.ViewAnimation
import com.shijc.wanandroidkotlin.widget.treeview.TreeNode
import com.shijc.wanandroidkotlin.widget.treeview.TreeViewAdapter
import kotlinx.android.synthetic.main.activity_todo.*
import java.util.*
import com.shijc.wanandroidkotlin.widget.TodoAddDialog
import android.support.v4.app.FragmentTransaction
import android.view.MenuItem
import com.shijc.wanandroidkotlin.common.base.SimpleDividerItemDecoration
import com.shijc.wanandroidkotlin.common.mvp.BaseActivity


class TodoActivity : BaseActivity() {

    private val list = ArrayList<TreeNode<*>>()

    private lateinit var adapter:TreeViewAdapter

    private lateinit var presenter:TodoPresenter
    private var type:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        presenter = TodoPresenter()
        presenter.attachView(mView)

        lyt_expand.visibility = View.GONE
        bt_toggle.setOnClickListener { toggleSection(bt_toggle) }
        bt_hide.setOnClickListener { toggleSection(bt_toggle) }
        bt_save.setOnClickListener {
            toggleSection(bt_toggle)
            type = radio_group.checkedRadioButtonId-1
            presenter.getData(type)
            tv_title.text = (radio_group.getChildAt(type) as AppCompatRadioButton).text.toString()

        }


        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用



        fab_add.setOnClickListener {
            showDialogFullscreen()
        }

        recycler_view.setLayoutManager(LinearLayoutManager(this))
        adapter = TreeViewAdapter(
            list, Arrays.asList(
                GroupViewBinder(), ChildViewBinder()
            )
        )

        //添加自定义分割线
        recycler_view.addItemDecoration(SimpleDividerItemDecoration(this))

        adapter.setOnTreeNodeListener(object :TreeViewAdapter.OnTreeNodeListener{
            override fun onClick(node: TreeNode<*>?, holder: RecyclerView.ViewHolder?): Boolean {
                if (node!!.isLeaf() && node.getContent() is ChildModel) {
                    val item = node.getContent() as ChildModel
                }
                return false
            }

            override fun onToggle(isExpand: Boolean, holder: RecyclerView.ViewHolder?) {
            }



        })
        recycler_view.setAdapter(adapter)
        recycler_view.setNestedScrollingEnabled(false)

        presenter.getData(type)

    }

    private fun toggleSection(view: View){
        var show = toggleArrow(view)
        if (show){
            ViewAnimation.expand(lyt_expand)
        }else{
            ViewAnimation.collapse(lyt_expand)
        }
    }

    private fun toggleArrow(view: View):Boolean{
        if (view.rotation.toInt() == 0) {
            view.animate().setDuration(200).rotation(180f);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0f);
            return false;
        }
    }



    private val mView = object :TodoContract.View{
        override fun onGetReuslt(datas: TodoResult.Data) {
            var group = GroupModel()
            group.name = "root"
            val groupsTitles = ArrayList<GroupModel>()
            var todoGroupTitle = GroupModel()
            todoGroupTitle.name = "待办清单"
            val groups = ArrayList<GroupModel>()
            datas.todoList.forEach{
                var todoGroup = GroupModel()
                todoGroup.name = TimeUtils.long2String(it.date,TimeUtils.FORMAT_TYPE_1)
                val todoChildren = ArrayList<ChildModel>()
                it.todoList.forEach{
                    var todoChild = ChildModel()
                    todoChild.name = it.title
                    todoChildren.add(todoChild)
                }
                todoGroup.users = todoChildren
                groups.add(todoGroup)
            }
            todoGroupTitle.groups = groups

            var doneGroupTitle = GroupModel()
            doneGroupTitle.name = "已完成清单"
            val groupsDone = ArrayList<GroupModel>()
            datas.doneList.forEach{
                var doneGroup = GroupModel()
                doneGroup.name = TimeUtils.long2String(it.date,TimeUtils.FORMAT_TYPE_1)
                val doneChildren = ArrayList<ChildModel>()
                it.todoList.forEach{
                    var doneChild = ChildModel()
                    doneChild.name = it.title
                    doneChildren.add(doneChild)
                }
                doneGroup.users = doneChildren
                groupsDone.add(doneGroup)
            }
            doneGroupTitle.groups = groupsDone

            groupsTitles.add(todoGroupTitle)
            groupsTitles.add(doneGroupTitle)

            group.groups = groupsTitles
            setGroupList(group)
        }

        override fun error(code: Int, msg: String) {
        }

        override fun failure(msg: String) {
        }

    }


    fun setGroupList(group: GroupModel?) {
        list.clear()

        if (group != null) {

            buildTreeNode(group!!)

            if (group!!.isLeaf()) {
                val rootHeight = 1
                for (node in list) {
                    node.setRootHeight(rootHeight)
                }
            }

            adapter.refresh(list)
        }


    }

    private fun buildTreeNode(group: GroupModel) {
        if (!CollectionUtils.isEmpty(group.getUsers())) {
            val users = group.getUsers()
            for (user in users) {
                val node = TreeNode(user)
                list.add(node)
            }
        }
        if (!group.isLeaf()) {
            for (groupItem in group.getGroups()) {
                groupItem.setParent(group)
                val node = TreeNode(groupItem)
                list.add(node)
                buildTreeNode(groupItem, node)
            }
        }

    }

    private fun buildTreeNode(group: GroupModel, parent: TreeNode<*>) {
        if (!CollectionUtils.isEmpty(group.getUsers())) {
            val users = group.getUsers()
            for (user in users) {
                val node = TreeNode(user)
                parent.addChild(node)
            }
        }
        if (!group.isLeaf()) {
            for (groupItem in group.getGroups()) {
                groupItem.setParent(group)
                val node = TreeNode(groupItem)
                parent.addChild(node)
                buildTreeNode(groupItem, node)
            }
        }

    }


    private fun showDialogFullscreen() {
        val fragmentManager = supportFragmentManager
        val newFragment = TodoAddDialog()
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit()
        newFragment.setOnCallbackResult(object : TodoAddDialog.CallbackResult {
            override fun sendResult(obj: Any) {

            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
