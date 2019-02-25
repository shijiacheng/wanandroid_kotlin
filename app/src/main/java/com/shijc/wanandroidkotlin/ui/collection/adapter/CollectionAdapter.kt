package com.shijc.wanandroidkotlin.ui.collection.adapter

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
import com.shijc.wanandroidkotlin.utils.StringUtils
import com.shijc.wanandroidkotlin.utils.TimeUtils

/**
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 10:37
 * @version V1.0
 */
class CollectionAdapter(private val context:Context, private val data:List<ArticleModel>) : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_article,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {

        if (holder is CollectionViewHolder) {
            holder?.tvAuthor?.text = "作者："+data[position].author
            holder?.tvTime?.text = "收藏时间："+TimeUtils.long2String(data[position].publishTime, TimeUtils.FORMAT_TYPE_1)
            holder?.tvContent?.text = data[position].title
            if (!StringUtils.isEmpty(data[position].chapterName)){
                holder?.tvClassify?.text = "分类：" + data[position].chapterName
            }

            holder.itemView.setOnClickListener {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class CollectionViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvAuthor: TextView = view.findViewById(R.id.tv_author)
        var tvTime: TextView = view.findViewById(R.id.tv_time)
        var tvContent: TextView = view.findViewById(R.id.tv_content)
        var tvClassify: TextView = view.findViewById(R.id.tv_classify)
    }

}