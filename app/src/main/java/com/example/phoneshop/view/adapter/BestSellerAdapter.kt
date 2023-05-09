package com.example.phoneshop.view.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Precision
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.phoneshop.R
import com.example.phoneshop.databinding.BestsellerRecyclerItemBinding
import com.example.phoneshop.model.response.BestSeller

class BestSellerAdapter(
    private var data: List<BestSeller> = listOf()
) : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    fun setData(dataNew: List<BestSeller>) {
        this.data = dataNew
        notifyDataSetChanged()
    }

    inner class BestSellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bestSeller: BestSeller) {
            val binding = BestsellerRecyclerItemBinding.bind(itemView)
            val cornerRadius: Float = 25f
            binding.imageBestSeller.load(bestSeller.picture) {
                placeholder(R.drawable.ic_no_photo_vector)
                error(R.drawable.ic_no_photo_vector)
                transformations(RoundedCornersTransformation(cornerRadius))
                precision(Precision.AUTOMATIC)
                scale(Scale.FILL)
            }
            binding.tvDiscountPrice.text = "$" + bestSeller.priceWithoutDiscount.toString()
            binding.tvPriceWithoutDiscount.apply {
                text = "$" + bestSeller.discountPrice.toString()
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
            binding.tvTitleBestseller.text = bestSeller.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val binding = BestsellerRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BestSellerViewHolder(binding.root)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        holder.bind(data[position])
    }
}