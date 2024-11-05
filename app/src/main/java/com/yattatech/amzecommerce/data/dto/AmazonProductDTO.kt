package com.yattatech.amzecommerce.data.dto

import com.google.gson.annotations.SerializedName

class AmazonProductsDTO(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: AmazonDataDTO
)

class AmazonDataDTO(
    val products: List<AmazonProductDTO>? = listOf()
)

class AmazonProductDTO(
    @SerializedName("product_title")
    val title: String?,
    @SerializedName("product_price")
    val price: String?,
    @SerializedName("product_photo")
    val imageUrl: String?,
    @SerializedName("product_availability")
    val productAvailability: String?,
    @SerializedName("asin")
    val asin: String?,
)
