package com.example.phoneshop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.phoneshop.KEY_BUNDLE
import com.example.phoneshop.databinding.FragmentProductDetailsBinding
import com.example.phoneshop.view.adapter.ProductDetailsPhonesAdapter
import com.example.phoneshop.viewmodel.ProductDetailsState
import com.example.phoneshop.viewmodel.ProductDetailsViewModel

class FragmentProductDetails : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding: FragmentProductDetailsBinding get() = _binding!!
    private val adapter = ProductDetailsPhonesAdapter()
    private val viewModel: ProductDetailsViewModel by lazy {
        ViewModelProvider(this)[ProductDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<ProductDetailsState> { renderData(it) }
        binding.recyclerViewProductDetails.adapter = adapter
        arguments?.getInt(KEY_BUNDLE)?.let {
            viewModel.getDetails()
        }
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, observer)
            //getDetails()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(state: ProductDetailsState) {
        when (state) {
            is ProductDetailsState.Error -> {}
            ProductDetailsState.Loading -> {}
            is ProductDetailsState.Success -> {
                adapter.setData(state.productDetailsDTO.images)
                with(binding) {
                    phoneName.text = state.productDetailsDTO.title
                    tvProc.text = state.productDetailsDTO.cPU
                    tvCamera.text = state.productDetailsDTO.camera
                    tvSd.text = state.productDetailsDTO.sd
                    tvRam.text = state.productDetailsDTO.ssd
                    addToBasket.text = "Add to Cart   $" + state.productDetailsDTO.price.toString()
                    rating.rating = state.productDetailsDTO.rating
                }

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): FragmentProductDetails {
            val fragment = FragmentProductDetails()
            fragment.arguments = bundle
            return fragment
        }
    }
}