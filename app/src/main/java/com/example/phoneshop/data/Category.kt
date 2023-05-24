package com.example.phoneshop.data

import com.example.phoneshop.R

data class Category(
    val category: String,
    val image: Int
)

fun getMyCategories(): List<Category> {
    return listOf(
        Category("Books", R.drawable.ic_books),
        Category("Health", R.drawable.ic_health),
        Category("Computer", R.drawable.ic_computer),
        Category("Phones", R.drawable.ic_phones)
    )
}
