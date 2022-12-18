package com.japps.justosolution.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.japps.justosolution.databinding.TemplateListaDatosBinding
import com.japps.justosolution.model.Result
import com.squareup.picasso.Picasso

class DataGenerateVH(view: View): RecyclerView.ViewHolder (view){
    private var binding = TemplateListaDatosBinding.bind(view)

    fun bind(item: Result){
        Picasso.get().load(item.picture.medium).into(binding.ivImagenHuman)
        binding.tvNombre.text = "${item.name.first} ${item.name.last}"
        binding.tvGender.text = item.gender
        binding.tvNationaliti.text = item.location.country
    }
}