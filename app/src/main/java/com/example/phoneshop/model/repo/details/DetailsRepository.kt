package com.example.phoneshop.model.repo.details

import com.example.phoneshop.viewmodel.ProductDetailsViewModel

interface DetailsRepository {
    fun getDetails(callback: ProductDetailsViewModel.CallBackDetails)
}