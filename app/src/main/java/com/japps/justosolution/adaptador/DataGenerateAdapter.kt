package com.japps.justosolution.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.japps.justosolution.R
import com.japps.justosolution.model.Result
import com.japps.justosolution.viewHolder.DataGenerateVH

class DataGenerateAdapter(val data: List<Result>): RecyclerView.Adapter<DataGenerateVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataGenerateVH {
        val  layoutInflater = LayoutInflater.from(parent.context)
        return DataGenerateVH(layoutInflater.inflate(R.layout.template_lista_datos, parent, false))
    }

    override fun onBindViewHolder(holder: DataGenerateVH, position: Int) {
        val items = data[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int = data.size

}