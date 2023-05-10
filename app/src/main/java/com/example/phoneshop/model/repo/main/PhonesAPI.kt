package com.example.phoneshop.model.repo.main

import com.example.phoneshop.BASE_ENDPOINT
import com.example.phoneshop.model.response.ResponseDTO
import retrofit2.Call
import retrofit2.http.GET

interface PhonesAPI {
    @GET(BASE_ENDPOINT)
    fun getPhones(): Call<ResponseDTO>
}