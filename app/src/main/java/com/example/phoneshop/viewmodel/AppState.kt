package com.example.phoneshop.viewmodel

import com.example.phoneshop.model.response.ResponseDTO

sealed class AppState {
    object Loading: AppState()
    data class Success(val responseDTO: ResponseDTO): AppState()
    data class Error(val error: Throwable): AppState()
}
