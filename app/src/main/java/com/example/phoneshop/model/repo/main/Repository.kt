package com.example.phoneshop.model.repo.main

import com.example.phoneshop.data.Category
import com.example.phoneshop.viewmodel.StoreViewModel


interface Repository {
    fun getHomeStorePhones(callback: StoreViewModel.Callback)
    fun getBestSellerPhones(callback: StoreViewModel.Callback)
    fun getCategories(): List<Category>
}