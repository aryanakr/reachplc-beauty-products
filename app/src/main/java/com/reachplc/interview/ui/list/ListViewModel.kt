package com.reachplc.interview.ui.list

import androidx.lifecycle.*
import com.reachplc.interview.data.Product
import com.reachplc.interview.di.AppContainer
import com.reachplc.interview.ui.detail.productServiceStatus
import kotlinx.coroutines.launch

enum class ProductServiceStatus { LOADING, ERROR, DONE}


class ListViewModel(private val appContainer: AppContainer) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _status = MutableLiveData<ProductServiceStatus>()
    val status: LiveData<ProductServiceStatus> = _status

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _status.value = ProductServiceStatus.LOADING
            try {
                _products.value = appContainer.retrofit.getProducts().products
                _status.value = ProductServiceStatus.DONE
            } catch (e: Exception) {
                _status.value = ProductServiceStatus.ERROR
                _products.value = listOf()
            }
        }
    }


}

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory (
    private val appContainer: AppContainer
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (ListViewModel(appContainer) as T)
}