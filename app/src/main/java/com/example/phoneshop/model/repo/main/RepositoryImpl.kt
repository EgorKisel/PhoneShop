package com.example.phoneshop.model.repo.main

import com.example.phoneshop.BASE_URL
import com.example.phoneshop.data.Category
import com.example.phoneshop.data.getMyCategories
import com.example.phoneshop.model.response.ResponseDTO
import com.example.phoneshop.viewmodel.StoreViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl : Repository {

    private val api = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    }.build().create(PhonesAPI::class.java)

    override fun getHomeStorePhones(callbackMy: StoreViewModel.Callback) {
        api.getPhones().enqueue(object : Callback<ResponseDTO> {
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callbackMy.onResponse(it)
                    }
                }
                response.body()?.homeStore
            }

            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                //Not yet implemented
            }
        })
    }

    override fun getBestSellerPhones(callbackMy: StoreViewModel.Callback) {
        api.getPhones().enqueue(object : Callback<ResponseDTO> {
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callbackMy.onResponse(it)
                    }
                }
                response.body()?.bestSeller
            }

            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                //Not yet implemented
            }
        })
    }

    override fun getCategories(): List<Category> = getMyCategories()
}