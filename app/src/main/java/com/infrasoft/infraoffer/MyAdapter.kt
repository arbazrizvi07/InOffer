package com.infrasoft.infraoffer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_list_item.view.*

class MyAdapter(private val myDataset: Array<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.offer_text.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}