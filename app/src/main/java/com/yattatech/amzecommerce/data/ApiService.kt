package com.yattatech.amzecommerce.data

import com.yattatech.amzecommerce.data.dto.AmazonProductsDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("x-rapidapi-key: REMOVED FOR SAFETY")
    @GET("/search")
    suspend fun getAmazonProductList(
        @Query("query") query: String = "Phone",
        @Query("page") page: Int,
        @Query("country") country: String = "US"
    ): AmazonProductsDTO
}