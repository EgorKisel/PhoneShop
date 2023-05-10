package com.example.phoneshop.viewmodel

import com.example.phoneshop.model.response.ProductDetailsDTO

sealed class ProductDetailsState {
    object Loading: ProductDetailsState()
    data class Success(val productDetailsDTO: ProductDetailsDTO): ProductDetailsState()
    data class Error(val error: Throwable): ProductDetailsState()
}
