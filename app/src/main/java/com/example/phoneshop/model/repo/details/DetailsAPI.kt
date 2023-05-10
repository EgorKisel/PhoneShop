package com.example.phoneshop.model.repo.details

import com.example.phoneshop.DETAILS_ENDPOINT
import com.example.phoneshop.model.response.ProductDetailsDTO
import retrofit2.Call
import retrofit2.http.GET

interface DetailsAPI {
    @GET(DETAILS_ENDPOINT)
    fun getProductDetails(): Call<ProductDetailsDTO>
}