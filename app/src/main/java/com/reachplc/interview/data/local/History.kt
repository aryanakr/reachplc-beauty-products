package com.reachplc.interview.data.local

import androidx.room.*
import com.reachplc.interview.data.Product
import java.util.*

@Entity(tableName = "history", foreignKeys = [
    ForeignKey(
        entity = Product::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("productId"),
        onDelete = ForeignKey.CASCADE
    )
])
data class History(
    @PrimaryKey@ColumnInfo(index = true)
    val productId: String,
    val visit: Date
)

data class ProductHistory(
    @Embedded val product: Product,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId"
    )
    val lastVisit: History?
)


