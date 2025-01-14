package com.example.food_delivery_app.core.orders.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.model.repository.OrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OrdersViewModel(val ordersRepository: OrdersRepository): ViewModel() {
    // Get Orders with specific status
    private val _ordersWithSpecificStatus = mutableStateOf<List<Order>>(emptyList())
    val ordersWithSpecificStatus: State<List<Order>> get() = _ordersWithSpecificStatus
    // -----------------------------------------------------------------------------------------------------------------------------




    // -----------------------------------------------------------------------------------------------------------------------------
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    // -----------------------------------------------------------------------------------------------------------------------------





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