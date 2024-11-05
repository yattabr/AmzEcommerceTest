package com.yattatech.amzecommerce

import app.cash.turbine.test
import com.yattatech.amzecommerce.data.AmazonDataRepository
import com.yattatech.amzecommerce.domain.model.AmazonProduct
import com.yattatech.amzecommerce.ui.productList.ProductListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: ProductListViewModel
    private lateinit var repository: AmazonDataRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        viewModel = ProductListViewModel(repository)
    }

    @Test
    fun `GIVEN repository returns products WHEN fetchProducts is called THEN products state is updated with success`() =
        runTest {
            val expectedProducts = listOf(
                AmazonProduct(title = "Product 1", price = "10.00", imageUrl = "url1"),
                AmazonProduct(title = "Product 2", price = "20.00", imageUrl = "url2")
            )

            coEvery { repository.getAmazonProductList() } returns flowOf(expectedProducts)


            viewModel.products.test {
                assertTrue(awaitItem().loading)
                viewModel.fetchProducts()
                assertTrue(awaitItem().iSuccess)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `GIVEN repository WHEN is fetching products THEN loading state is updated`() = runTest {
        coEvery { repository.getAmazonProductList() } returns flowOf(emptyList())

        viewModel.products.test {
            assertTrue(awaitItem().loading)
            viewModel.fetchProducts()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `GIVEN repository throws exception WHEN fetchProducts is called THEN products state is updated with error`() =
        runTest {
            val errorMessage = "Network error"
            coEvery { repository.getAmazonProductList() } returns flow {
                emit(emptyList())
                throw RuntimeException(errorMessage)
            }

            viewModel.products.test {
                assertTrue(awaitItem().loading)
                viewModel.fetchProducts()
                assertTrue(awaitItem().errorMessage == errorMessage)

                cancelAndIgnoreRemainingEvents()
            }
        }
}