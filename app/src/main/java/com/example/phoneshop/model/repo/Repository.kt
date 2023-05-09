package com.example.phoneshop.model.repo

import com.example.phoneshop.viewmodel.StoreViewModel


interface Repository {
    fun getHomeStorePhones(callback: StoreViewModel.Callback)
    fun getBestSellerPhones(callback: StoreViewModel.Callback)
}