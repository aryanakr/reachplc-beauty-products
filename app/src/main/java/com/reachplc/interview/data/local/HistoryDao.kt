package com.reachplc.interview.data.local

import androidx.room.*
import com.reachplc.interview.data.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    /**
     * Inserting new history to database
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(history: History) : Long

    /**
     * Inserting new product to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    /**
     * updating a history in database from an id
     */
    @Update
    suspend fun updateHistory(history: History)

    /**
     * retrieving a product from database using id
     */
    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getProductById(id: String): List<Product>

    /**
     * retrieving a product and its history as ProductHistory using id
     */
    @Transaction
    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductHistoryById(id: String): Flow<ProductHistory>;

    /**
     * retrieving all products and their history
     */
    @Transaction
    @Query("SELECT * FROM product")
    fun getProductsHistory(): Flow<List<ProductHistory>>;
}