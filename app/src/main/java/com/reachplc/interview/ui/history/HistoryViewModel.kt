package com.reachplc.interview.ui.history

import androidx.lifecycle.*
import com.reachplc.interview.data.Product
import com.reachplc.interview.data.local.History
import com.reachplc.interview.data.local.HistoryDao
import com.reachplc.interview.data.local.ProductHistory
import kotlinx.coroutines.launch
import java.util.*

/**
 * View Model for History fragment
 * @param historyDao HistoryDao access to history databse
 */
class HistoryViewModel (private val historyDao: HistoryDao) : ViewModel() {

    /**
     * Retrieves all product histories from local database
     */
    fun retrieveAllProductsHistory(): LiveData<List<ProductHistory>> {
        return historyDao.getProductsHistory().asLiveData()
    }
}

class HistoryViewModelFactory(private val historyDao: HistoryDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(historyDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}