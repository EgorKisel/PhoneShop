package com.example.phoneshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phoneshop.model.repo.details.DetailsRepository
import com.example.phoneshop.model.repo.details.DetailsRepositoryImpl
import com.example.phoneshop.model.response.ProductDetailsDTO

class ProductDetailsViewModel(
    private val liveData: MutableLiveData<ProductDetailsState> = MutableLiveData(),
    private val repository: DetailsRepository = DetailsRepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getDetails() {
        repository.getDetails(callback)
    }

    private val callback = object : CallBackDetails {
        override fun onResponse(productDetailsDTO: ProductDetailsDTO) {
            liveData.postValue(ProductDetailsState.Success(productDetailsDTO))
        }

        override fun onFail() {
            liveData.postValue(ProductDetailsState.Error(Throwable()))
        }

    }

    interface CallBackDetails {
        fun onResponse(productDetailsDTO: ProductDetailsDTO)
        fun onFail()
    }
}