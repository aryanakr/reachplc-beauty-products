package com.reachplc.interview.di

import com.reachplc.interview.data.remote.ProductsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainer {

    val retrofit: ProductsService = Retrofit.Builder()
        .baseUrl("https://apps-tests.s3-eu-west-1.amazonaws.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ProductsService::class.java)

}
