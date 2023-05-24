package com.example.phoneshop.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.phoneshop.R
import com.example.phoneshop.data.Category
import com.example.phoneshop.databinding.SelectCategoryRecyclerItemBinding

class SelectCategoryAdapter(
    private var data: List<Category> = listOf()
) : RecyclerView.Adapter<SelectCategoryAdapter.CategoryHolder>() {

    fun setData(dataNew: List<Category>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    inner class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            val binding = SelectCategoryRecyclerItemBinding.bind(itemView)
            binding.tvSelectCategory.text = category.category
            binding.imageSelectCategory.load(category.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding = SelectCategoryRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(data[position])
    }
}