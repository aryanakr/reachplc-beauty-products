package com.reachplc.interview.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.reachplc.interview.data.Product
import java.text.NumberFormat
import java.util.*

/**
 * Creates a compose grid of products in viewmodel
 */
@ExperimentalFoundationApi
@Composable
fun ProductsGrid(
    viewModel: ListViewModel,
    navigateToDetail: (String, String) -> Unit
) {
    val status by viewModel.status.observeAsState()

    when(status){
        ProductServiceStatus.LOADING -> {
            Column(modifier = Modifier.fillMaxHeight(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("Loading")
            }
        }
        ProductServiceStatus.DONE -> {
            val products : List<Product> by viewModel.products.observeAsState(listOf())

            if (products.isNotEmpty()) {
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
        ProductServiceStatus.ERROR -> {
            Column(modifier = Modifier.fillMaxHeight(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("Network Error?")
            }
        }
    }

}

/**
 * Compose card for product
 */
@Composable
fun ProductsGridItem(product: Product,modifier: Modifier = Modifier) {
    Card (
        modifier = modifier.padding(4.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            // Product card image thumbnail
            Image(
                painter = rememberImagePainter(product.image),
                contentDescription = product.name + " thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 250.dp),
                contentScale = ContentScale.Crop
            )

            // Product card detail
            ProductInfoBox(product.name, product.price)
        }
    }
}

@Preview("Products Grid Item")
@Composable
fun PreviewGridItem(){
    val sample = Product(
        id = UUID.randomUUID().toString(),
        name = "sample product",
        description = "description of sample product",
        image = "https://via.placeholder.com/150",
        price = 1.5)

    ProductsGridItem(sample)
}

/**
 * Compose product card information box
 */
@Composable
fun ProductInfoBox(name: String, price: Double, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.width(8.dp))
        val priceTxt = NumberFormat.getCurrencyInstance().format(price)

        Text(
            text = priceTxt,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
        )
    }

}