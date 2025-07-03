package com.example.mediapp.models

data class GetProductResponseItem(
    val category: String,
    val id: Int,
    val name: String,
    val price: Double,
    val products_id: String,
    val stock: Int
)