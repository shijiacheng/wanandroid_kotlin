package com.shijc.wanandroidkotlin.ui.todo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.todo.bean.ChildModel
import com.shijc.wanandroidkotlin.ui.todo.bean.GroupModel
import com.shijc.wanandroidkotlin.widget.treeview.TreeNode
import com.shijc.wanandroidkotlin.widget.treeview.TreeViewBinder

/**
 * @Package com.shijc.wanandroidkotlin.ui.todo.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/26 上午 10:22
 * @version V1.0
 */
class ChildViewBinder  : TreeViewBinder<ChildViewBinder.ChildViewHolder>(){
    override fun bindView(holder: ChildViewHolder?, position: Int, node: TreeNode<*>?) {
        val item = node?.getContent() as ChildModel
        holder?.text?.setText(item.name)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_todo_child
    }

    override fun provideViewHolder(itemView: View?): ChildViewHolder {
        return ChildViewHolder(itemView!!)
    }

    inner class ChildViewHolder(rootView: View) : TreeViewBinder.ViewHolder(rootView){
        var text: TextView = rootView.findViewById(R.id.view_item_child_tv)
    }
}
