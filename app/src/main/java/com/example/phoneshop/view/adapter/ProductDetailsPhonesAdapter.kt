package com.example.phoneshop.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.phoneshop.databinding.ProductDetailsPhonesItemBinding
import com.example.phoneshop.model.response.ProductDetailsDTO

class ProductDetailsPhonesAdapter(
    private var data: List<String> = listOf()
) : RecyclerView.Adapter<ProductDetailsPhonesAdapter.DetailsViewHolder>() {

    fun setData(dataNew: List<String>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    inner class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(images: String) {
            val binding = ProductDetailsPhonesItemBinding.bind(itemView)
            binding.imageDetails.load(images)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding = ProductDetailsPhonesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailsViewHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(data[position])
    }
}