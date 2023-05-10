package com.example.phoneshop.model.repo.details

import com.example.phoneshop.BASE_URL
import com.example.phoneshop.model.response.ProductDetailsDTO
import com.example.phoneshop.viewmodel.ProductDetailsViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryImpl : DetailsRepository {

    private val api = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    }.build().create(DetailsAPI::class.java)

    override fun getDetails(callbackMy: ProductDetailsViewModel.CallBackDetails) {
        api.getProductDetails().enqueue(object : Callback<ProductDetailsDTO> {
            override fun onResponse(
                call: Call<ProductDetailsDTO>,
                response: Response<ProductDetailsDTO>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callbackMy.onResponse(it)
                    }
                }
            }

            override fun onFailure(call: Call<ProductDetailsDTO>, t: Throwable) {
                // Not yet implemented
            }

        })
    }
}