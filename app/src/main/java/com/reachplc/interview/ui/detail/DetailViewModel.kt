package com.reachplc.interview.ui.detail


import androidx.lifecycle.*
import com.reachplc.interview.data.Product
import com.reachplc.interview.data.local.History
import com.reachplc.interview.data.local.HistoryDao
import com.reachplc.interview.di.AppContainer
import com.reachplc.interview.ui.list.ListViewModel
import kotlinx.coroutines.launch
import java.util.*

enum class productServiceStatus { LOADING, ERROR, DONE}


class DetailViewModel(private val appContainer: AppContainer, private val historyDao: HistoryDao) : ViewModel() {
    // TODO: Implement the ViewModel
    lateinit var productId: String
    private val _status = MutableLiveData<productServiceStatus>()

    val status: LiveData<productServiceStatus> = _status

    private val _product = MutableLiveData<Product?>()
    val product: MutableLiveData<Product?> = _product

    fun fetchProduct(id: String) {
        productId = id
        viewModelScope.launch {
            _status.value = productServiceStatus.LOADING
            try {
                _product.value = appContainer.retrofit.getProducts().products.first{ it.id == productId}
                _status.value = productServiceStatus.DONE
            } catch (e: Exception) {
                _status.value = productServiceStatus.ERROR
                _product.value = null
            }
        }
    }

    fun saveProductHistory(product: Product, access: Date){
        viewModelScope.launch {
            if (historyDao.getProductById(product.id).isEmpty()){
                historyDao.insertProduct(product)

                val history = History(productId = product.id, visit = access)
                historyDao.insertHistory(history)
            } else {
                val history = History(productId = product.id, visit = access)
                historyDao.updateHistory(history)
            }

        }

    }

}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory (
    private val appContainer: AppContainer,
    private val historyDao: HistoryDao
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (DetailViewModel(appContainer, historyDao) as T)
}