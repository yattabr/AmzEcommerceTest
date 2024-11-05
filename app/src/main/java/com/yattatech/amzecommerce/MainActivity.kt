package com.yattatech.amzecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yattatech.amzecommerce.domain.model.AmazonProduct
import com.yattatech.amzecommerce.ui.productList.ProductDetailScreen
import com.yattatech.amzecommerce.ui.productList.ProductListScreen
import com.yattatech.amzecommerce.ui.theme.Container

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Container { Navigation() }
        }
    }

    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        var amazonProduct = AmazonProduct()
        NavHost(navController = navController, startDestination = HOME) {
            composable(HOME) {
                ProductListScreen {
                    amazonProduct = it
                    navController.navigate(PRODUCT_DETAIL)
                }
            }
            composable(route = PRODUCT_DETAIL) {
                ProductDetailScreen(amazonProduct)
            }
        }
    }

    companion object {
        const val HOME = "productList"
        const val PRODUCT_DETAIL = "productDetail"
    }
}
