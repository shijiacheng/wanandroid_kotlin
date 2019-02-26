package com.shijc.wanandroidkotlin.ui.todo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.todo.bean.GroupModel
import com.shijc.wanandroidkotlin.widget.treeview.TreeNode
import com.shijc.wanandroidkotlin.widget.treeview.TreeViewBinder

/**
 * @Package com.shijc.wanandroidkotlin.ui.todo.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/26 上午 10:05
 * @version V1.0
 */
class GroupViewBinder : TreeViewBinder<GroupViewBinder.GroupViewHolder>(){
    override fun bindView(holder: GroupViewHolder?, position: Int, node: TreeNode<*>?) {
        val item = node?.getContent() as GroupModel
        holder?.image?.rotation = (if (node.isExpand()) 90 else 0).toFloat()
        holder?.text?.setText(item.name)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_todo_group
    }

    override fun provideViewHolder(itemView: View?): GroupViewHolder {
        return GroupViewHolder(itemView!!)
    }

    inner class GroupViewHolder(rootView: View) : TreeViewBinder.ViewHolder(rootView){
        var text:TextView = rootView.findViewById(R.id.view_item_group_tv)
        var image:ImageView = rootView.findViewById(R.id.view_item_group_iv)
    }
}
