package com.reachplc.interview.data.local

import androidx.room.*
import com.reachplc.interview.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Transaction
    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductHistoryById(id: String): Flow<ProductHistory>;

    @Transaction
    @Query("SELECT * FROM product")
    fun getProductsHistory(): Flow<List<ProductHistory>>;
}