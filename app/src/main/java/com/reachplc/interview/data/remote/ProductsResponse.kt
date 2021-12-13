package com.reachplc.interview.data.remote

import com.reachplc.interview.data.Product

data class ProductsResponse(
    val products: List<Product> = emptyList()) {

}
