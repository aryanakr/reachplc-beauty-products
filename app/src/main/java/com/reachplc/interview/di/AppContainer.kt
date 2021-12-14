package com.reachplc.interview.di

import androidx.navigation.fragment.NavHostFragment
import com.reachplc.interview.R
import com.reachplc.interview.data.remote.ProductsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainer (service: ProductsService) {

    val productsService: ProductsService = service

}
