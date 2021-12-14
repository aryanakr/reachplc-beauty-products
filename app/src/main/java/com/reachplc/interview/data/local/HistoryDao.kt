package com.reachplc.interview.data.local

import androidx.room.*
import com.reachplc.interview.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(history: History) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateHistory(history: History)

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getProductById(id: String): List<Product>

    @Transaction
    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductHistoryById(id: String): Flow<ProductHistory>;

    @Transaction
    @Query("SELECT * FROM product")
    fun getProductsHistory(): Flow<List<ProductHistory>>;
}