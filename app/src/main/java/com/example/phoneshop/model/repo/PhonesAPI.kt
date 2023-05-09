package com.example.phoneshop.model.repo

import com.example.phoneshop.model.response.ResponseDTO
import retrofit2.Call
import retrofit2.http.GET

interface PhonesAPI {
    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    fun getPhones(): Call<ResponseDTO>
}