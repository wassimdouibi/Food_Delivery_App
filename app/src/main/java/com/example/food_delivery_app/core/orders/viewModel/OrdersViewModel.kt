package com.example.food_delivery_app.core.orders.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.orders.model.entity.Cart
import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.model.repository.OrdersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OrdersViewModel(val ordersRepository: OrdersRepository): ViewModel() {
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _ordersWithSpecificStatus = mutableStateOf<List<Order>>(emptyList())
    val ordersWithSpecificStatus: State<List<Order>> get() = _ordersWithSpecificStatus
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _addMenuToCartStatus = MutableStateFlow<Int?>(null)
    val addMenuToCartStatus: StateFlow<Int?> = _addMenuToCartStatus
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _cartCreation = MutableStateFlow<Cart?>(null)
    val cartCreation: StateFlow<Cart?> = _cartCreation
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _selectedCart = MutableStateFlow<Cart?>(null)
    val selectedCart: StateFlow<Cart?> = _selectedCart
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _ordersByUserId = MutableStateFlow<List<Order>>(emptyList())
    val ordersByUserId: StateFlow<List<Order>> = _ordersByUserId
    // -----------------------------------------------------------------------------------------------------------------------------


    // -----------------------------------------------------------------------------------------------------------------------------
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    // -----------------------------------------------------------------------------------------------------------------------------


    // -------- getting all the orders function ---------
    fun getOrdersByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    ordersRepository.getOrdersByUserId(userId)
                }
                _ordersByUserId.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
    // -------- deleting order funtion ----------
    fun deleteOrder(orderId: Int,) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                Log.d("deleteOrder", "order before deleting")
                val response = withContext(Dispatchers.IO) {
                    ordersRepository.deleteOrderById(orderId)
                }
                Log.d("deleteOrder", "order delete : $response")
                if(!response) {
                    _error.value = "order still exist"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
    // -------- Creating order funtion ----------
    fun createOrder(orderRequest: Order) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                withContext(Dispatchers.IO) {
                    ordersRepository.createOrder(orderRequest)
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addMenuToCart(userId: Int, menuId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    ordersRepository.addMenuToCart(userId, menuId)
                }
                _addMenuToCartStatus.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createCart(cart: Order) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    ordersRepository.createOrder(cart)
                }
                Log.d("addMenu", "creating cart : $response")

            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCartByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    ordersRepository.getCartByUserId(userId)
                }
                Log.d("addMenu", "getting cart by userid : $response")
                _selectedCart.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun deleteCartByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    ordersRepository.deleteCartByUserId(userId)
                }
                Log.d("addMenu", "deleted cart by userid : $response")
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }






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