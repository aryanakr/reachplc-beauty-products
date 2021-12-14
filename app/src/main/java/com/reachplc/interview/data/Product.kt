package com.reachplc.interview.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val id: String,
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val price: Double = 0.0,
)
