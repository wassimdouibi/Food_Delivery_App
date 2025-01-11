package com.example.food_delivery_app.core.orders.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.model.repository.OrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OrdersViewModel(val ordersRepository: OrdersRepository): ViewModel() {
    // Get Orders with specific status
    private val _ordersWithSpecificStatus = mutableStateOf<List<Order>>(emptyList())
    val ordersWithSpecificStatus: State<List<Order>> get() = _ordersWithSpecificStatus
    // -----------------------------------------------------------------------------------------------------------------------------

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun getOrdersWithSpecificStatus(status: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    ordersRepository.getOrdersWithSpecificStatus(status)
                }
                _ordersWithSpecificStatus.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    class Factory(private val ordersRepository: OrdersRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OrdersViewModel(ordersRepository) as T
        }
    }
}