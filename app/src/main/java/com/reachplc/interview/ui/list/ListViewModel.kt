package com.reachplc.interview.ui.list

import androidx.lifecycle.*
import com.reachplc.interview.data.Product
import com.reachplc.interview.di.AppContainer
import kotlinx.coroutines.launch

enum class productServiceStatus { LOADING, ERROR, DONE}


class ListViewModel(private val appContainer: AppContainer) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _status = MutableLiveData<productServiceStatus>()

    val status: LiveData<productServiceStatus> = _status

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _status.value = productServiceStatus.LOADING
            try {
                _products.value = appContainer.retrofit.getProducts().products
                _status.value = productServiceStatus.DONE
            } catch (e: Exception) {
                _status.value = productServiceStatus.ERROR
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