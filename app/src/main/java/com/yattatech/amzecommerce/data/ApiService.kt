package com.yattatech.amzecommerce.data

import com.yattatech.amzecommerce.data.dto.AmazonProductsDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("x-rapidapi-key: cc7630f1dcmsh81f2d37643bc0a5p1bd716jsn65b9a153e547")
    @GET("/search")
    suspend fun getAmazonProductList(
        @Query("query") query: String = "Phone",
        @Query("page") page: Int,
        @Query("country") country: String = "US"
    ): AmazonProductsDTO
}