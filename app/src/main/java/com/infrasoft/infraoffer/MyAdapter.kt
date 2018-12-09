package com.infrasoft.infraoffer

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_list_item.view.*

class MyAdapter(private val myDataset: ArrayList<Offer>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.offer_text.text = myDataset.get(position).dealname
        holder.view.list_item.setOnClickListener {
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", myDataset.get(position).offerId)
            //context.startActivity(intent)
        }

    }

    override fun getItemCount() = myDataset.size

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}