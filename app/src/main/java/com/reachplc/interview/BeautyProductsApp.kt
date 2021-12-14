package com.reachplc.interview

import android.app.Application
import com.reachplc.interview.data.remote.ProductsService
import com.reachplc.interview.di.AppContainer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BeautyProductsApp : Application() {

    // Instance of AppContainer that will be used by all the Activities of the app
    val retrofit: ProductsService = Retrofit.Builder()
        .baseUrl("https://apps-tests.s3-eu-west-1.amazonaws.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ProductsService::class.java)

    val appContainer = AppContainer(retrofit)

}
