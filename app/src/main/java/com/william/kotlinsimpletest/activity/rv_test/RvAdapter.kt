package com.william.kotlinsimpletest.activity.rv_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.william.kotlinsimpletest.R

/**
 * date: 2019-12-03
 * author: hwj
 * description:
 */
class RvAdapter : RecyclerView.Adapter<RvAdapter.VHolder>() {
    private val list: ArrayList<Int> = ArrayList()

    init {
        for (i in 0..15) {
            list.add(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return VHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.tv.text=list[position].toString()
    }

    class VHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.tv_rv_item)
    }
}