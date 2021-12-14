package com.reachplc.interview


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reachplc.interview.data.Product
import com.reachplc.interview.ui.list.ProductsGridItem
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductsGridTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    // TODO: Product Card Content Test
    @Test
    fun ProductsGridItem_ProductNameDisplayed() {
        val testProduct = Product(
            id = "id",
            price = 10.0,
            description = "test product description",
            name = "test",
            image = ""
        )

        composeTestRule.setContent {

            ProductsGridItem(product = testProduct)
        }

        composeTestRule
            .onNodeWithText(testProduct.name)
            .assertIsDisplayed()


    }

    // TODO: Product Grid Content Test
}