package com.yattatech.amzecommerce.ui.productList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.yattatech.amzecommerce.domain.model.AmazonProduct
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = koinViewModel(),
    onNavigateToProductDetail: (AmazonProduct) -> Unit = {}
) {
    val productsState by viewModel.products.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (!productsState.iSuccess) {
            viewModel.fetchProducts()
        }
    }

    when {
        productsState.loading -> Loading()
        productsState.iSuccess -> {
            ProductListContent(
                products = productsState.products
            ){
                onNavigateToProductDetail(it)
            }
        }

        productsState.errorMessage.isNotEmpty() -> {}
    }

}

@Composable
fun ProductListContent(
    products: List<AmazonProduct>,
    onNavigateToProductDetail: (AmazonProduct) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        LazyColumn {
            items(products) {
                HorizontalDivider(thickness = 2.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            onNavigateToProductDetail(it)
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(end = 8.dp)
                            .align(CenterVertically),
                        model = it.imageUrl,
                        contentDescription = ""
                    )

                    Text(modifier = Modifier.align(CenterVertically), text = it.title)
                    Text(modifier = Modifier.align(CenterVertically), text = it.price)
                }
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .size(64.dp),
        contentAlignment = Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Preview
@Composable
fun ProductListContentPreview() {
    ProductListContent(
        listOf(
            AmazonProduct(
                "iPhone", "12.00", ""
            ), AmazonProduct(
                "iPhone", "12.00", ""
            ), AmazonProduct(
                "iPhone", "12.00", ""
            )
        )
    )
}