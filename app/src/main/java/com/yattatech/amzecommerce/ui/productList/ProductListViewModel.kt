package com.yattatech.amzecommerce.ui.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yattatech.amzecommerce.data.AmazonDataRepository
import com.yattatech.amzecommerce.domain.model.AmazonProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class ProductListViewModel(private val amazonDataRepository: AmazonDataRepository) : ViewModel() {

    private val _products = MutableStateFlow(AmazonProductState())
    val products: StateFlow<AmazonProductState> = _products.asStateFlow()

    fun fetchProducts(page: Int = 1) {
        viewModelScope.launch {
            _products.value = loadingReducer
            amazonDataRepository.getAmazonProductList(page).collect {
                _products.value = try {
                    successReducer(it)
                } catch (e: Exception) {
                    errorReducer(e.message.orEmpty())
                }
            }
        }
    }

    private val loadingReducer = _products.value.copy(loading = true)

    private fun successReducer(product: List<AmazonProduct>) = _products.value.copy(
        loading = false,
        iSuccess = true,
        products = product
    )

    private fun errorReducer(errorMessage: String) = _products.value.copy(
        loading = false,
        iSuccess = false,
        errorMessage = errorMessage
    )
}

data class AmazonProductState(
    val loading: Boolean = true,
    val iSuccess: Boolean = false,
    val products: List<AmazonProduct> = emptyList(),
    val errorMessage: String = ""
)