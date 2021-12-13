package com.reachplc.interview.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.reachplc.interview.data.Product

@ExperimentalFoundationApi
@Composable
fun ProductsGrid(
    viewModel: ListViewModel,
    navigateToDetail: (String, String) -> Unit
) {
    val products : List<Product> by viewModel.products.observeAsState(listOf())

    if (products.size > 0) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(products.size) { i ->
                ProductsGridItem(product = products[i], modifier = Modifier.clickable {
                    navigateToDetail(products[i].id, products[i].name)
                })
            }
        }
    } else {
        Text("There is no product to show!!!")
    }
}

@Composable
fun ProductsGridItem(product: Product,modifier: Modifier = Modifier) {
    Card (
        modifier = modifier.padding(4.dp),
        backgroundColor = Color.LightGray
    ){
        Column()
        {

            // Image Thumbnail
            Image(
                painter = rememberImagePainter(product.image),
                contentDescription = product.name + " thumbnail",
                modifier = Modifier.size(128.dp)
            )

            ProductInfoBox(product.name, product.price)

        }
    }
}

@Composable
fun ProductInfoBox(name: String, price: Double, modifier: Modifier = Modifier) {
    Text(
        text = name,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )
    // TODO: make price localised
    Text(
        text = price.toString(),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(16.dp)
    )
}