package com.yattatech.amzecommerce.domain.model

data class AmazonProduct(
    val asin: String = "",
    val title: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val productAvailability: String = "",
)