package com.example.phoneshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.phoneshop.model.repo.main.Repository
import com.example.phoneshop.model.repo.main.RepositoryImpl
import com.example.phoneshop.model.response.ResponseDTO

class StoreViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getHomeStore() {
        repository.getHomeStorePhones(callback)
    }

    fun getBestSeller() {
        repository.getBestSellerPhones(callback)
    }
    fun getCategories() {
        repository.getCategories()
    }

    private val callback = object : Callback {

        override fun onResponse(responseDTO: ResponseDTO) {
            liveData.postValue(AppState.Success(responseDTO))
        }

        override fun onFail() {
            liveData.postValue(AppState.Error(Throwable()))
        }

    }

    interface Callback {
        fun onResponse(responseDTO: ResponseDTO)
        fun onFail()
    }
}