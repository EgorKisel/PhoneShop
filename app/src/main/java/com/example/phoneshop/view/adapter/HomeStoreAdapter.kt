package com.example.phoneshop.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.example.phoneshop.R
import com.example.phoneshop.databinding.HomeStorePhoneItemBinding
import com.example.phoneshop.model.response.HomeStore

class HomeStoreAdapter(
    private var data: List<HomeStore> = listOf()
) : RecyclerView.Adapter<HomeStoreAdapter.HomeViewHolder>() {

    fun setData(dataNew: List<HomeStore>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeStorePhoneItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return HomeViewHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(homeStore: HomeStore) {
            val binding = HomeStorePhoneItemBinding.bind(itemView)
            binding.tvNameStorePhone.text = homeStore.title
            binding.tvSubtitleHot.text = homeStore.subtitle
            if (homeStore.isNew) {
                binding.imageNew.load(R.drawable.baseline_new)
            }
            binding.imageStorePhone.load(homeStore.picture) {
                precision(Precision.AUTOMATIC)
                //scale(Scale.FILL)
                //size(600, 1200)
            }
        }
    }
}