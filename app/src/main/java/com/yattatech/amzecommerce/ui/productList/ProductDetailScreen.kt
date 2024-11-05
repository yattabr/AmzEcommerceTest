package com.yattatech.amzecommerce.ui.productList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yattatech.amzecommerce.domain.model.AmazonProduct

@Composable
fun ProductDetailScreen(amazonProduct: AmazonProduct) {
    ProductDetailContent(amazonProduct)
}

@Composable
fun ProductDetailContent(amazonProduct: AmazonProduct) {
    var isFavorite by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(16.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            IconButton(modifier = Modifier.align(End),
                onClick = { isFavorite = !isFavorite }) {
                if (isFavorite)
                    Icon(Icons.Default.Favorite, contentDescription = "")
                else
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "")

            }

            AsyncImage(
                modifier = Modifier
                    .align(CenterHorizontally),
                model = amazonProduct.imageUrl,
                contentDescription = ""
            )
            Text(
                text = amazonProduct.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(thickness = 2.dp)

            Text(
                text = amazonProduct.price,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = amazonProduct.productAvailability,
                fontStyle = FontStyle.Italic
            )
        }
    }

}