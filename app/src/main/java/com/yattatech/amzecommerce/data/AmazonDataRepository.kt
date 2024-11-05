package com.yattatech.amzecommerce.data

import com.yattatech.amzecommerce.domain.model.AmazonProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AmazonDataRepository {
    suspend fun getAmazonProductList(page: Int = 1): Flow<List<AmazonProduct>>
}

class AmazonDataRepositoryImpl(private val apiService: ApiService) : AmazonDataRepository {
    override suspend fun getAmazonProductList(page: Int): Flow<List<AmazonProduct>> {
        return flow {
            val response = apiService.getAmazonProductList(page = page)
            val amazonProductList = response.data.products?.map { data ->
                AmazonProduct(
                    title = data.title.orEmpty(),
                    price = data.price.orEmpty(),
                    imageUrl = data.imageUrl.orEmpty(),
                    productAvailability = data.productAvailability.orEmpty(),
                    asin = data.asin.orEmpty()
                )
            }
            emit(amazonProductList ?: listOf())
        }
    }
}

