package com.example.phoneshop.model.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDTO(
    @SerializedName("best_seller")
    val bestSeller: List<BestSeller>,
    @SerializedName("home_store")
    val homeStore: List<HomeStore>
): Parcelable