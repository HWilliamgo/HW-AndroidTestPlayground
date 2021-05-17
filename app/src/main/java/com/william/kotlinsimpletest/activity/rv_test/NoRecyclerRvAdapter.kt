package com.william.kotlinsimpletest.activity.rv_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.william.kotlinsimpletest.R

/**
 * date: 2021/5/14
 * author: HWilliamgo
 * description:
 */
class NoRecyclerRvAdapter(val dataList: ArrayList<String>, val rv: RecyclerView) :
    RecyclerView.Adapter<NoRecyclerRvAdapter.NoRecyclerViewHolder>() {
    companion object {
        private const val NO_RECYCLE_VIEW_TYPE = 1
    }

    init {
        rv.recycledViewPool.setMaxRecycledViews(NO_RECYCLE_VIEW_TYPE, 0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoRecyclerViewHolder {
        LogUtils.d("onCreateViewHolder")
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_no_recycle, parent, false)
        return NoRecyclerViewHolder(itemView).apply {

        }
    }

    override fun onBindViewHolder(holder: NoRecyclerViewHolder, position: Int) {
//        holder.setIsRecyclable(false)
        LogUtils.d("onBindViewHolder")
        if (!holder.hasInit) {
            holder.tv.text = dataList[position]
        }
    }

    override fun onViewRecycled(holder: NoRecyclerViewHolder) {
        LogUtils.d("onViewRecycled -> ${holder.adapterPosition}")
    }

    override fun getItemViewType(position: Int): Int {
        return NO_RECYCLE_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class NoRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hasInit: Boolean = false
        val tv: TextView by lazy { itemView.findViewById<TextView>(R.id.tv_no_recycle) }
    }
}