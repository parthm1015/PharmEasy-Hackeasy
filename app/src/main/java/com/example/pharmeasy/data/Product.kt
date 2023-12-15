package com.example.pharmeasy.data

data class SearchProductResponse(
    val status: Long,
    val data: Data
)

data class Data(
    val products: List<Product>
)

data class Product(
    val productId: Long,
    val name2: String
)
