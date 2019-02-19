package com.shijc.wanandroidkotlin.ui.systemtree.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.shijc.wanandroidkotlin.R
import com.shijc.wanandroidkotlin.ui.home.bean.ArticleModel
import com.shijc.wanandroidkotlin.utils.TimeUtils

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 10:37
 * @version V1.0
 */
class SystemTreeListAdapter(private val context:Context, private val data:List<ArticleModel>) : RecyclerView.Adapter<SystemTreeListAdapter.SystemTreeListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemTreeListViewHolder {
        return SystemTreeListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_article,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SystemTreeListViewHolder, position: Int) {

        if (holder is SystemTreeListViewHolder) {
            holder?.tvAuthor?.text = data[position].author
            holder?.tvTime?.text = TimeUtils.long2String(data[position].publishTime, TimeUtils.FORMAT_TYPE_1)
            holder?.tvContent?.text = data[position].title
            holder?.tvClassify?.text = data[position].superChapterName

            holder.itemView.setOnClickListener {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class SystemTreeListViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvAuthor: TextView = view.findViewById(R.id.tv_author)
        var tvTime: TextView = view.findViewById(R.id.tv_time)
        var tvContent: TextView = view.findViewById(R.id.tv_content)
        var tvClassify: TextView = view.findViewById(R.id.tv_classify)
    }

}