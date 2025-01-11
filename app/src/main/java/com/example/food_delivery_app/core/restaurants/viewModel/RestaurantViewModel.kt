package com.example.food_delivery_app.core.restaurants.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.restaurants.model.repository.RestaurantRepository
import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantViewModel(val restaurantRepository: RestaurantRepository): ViewModel() {

    private val _restaurants = mutableStateOf<List<RestaurantResponse>>(emptyList())
    val restaurants: State<List<RestaurantResponse>> = _restaurants
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _selectedRestaurant = mutableStateOf<RestaurantResponse?>(null)
    val selectedRestaurant: State<RestaurantResponse?> = _selectedRestaurant // get selected restaurant
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _foodsFromRestaurant = mutableStateOf<List<FoodResponse>>(emptyList())
    val foodsFromRestaurant: State<List<FoodResponse>> = _foodsFromRestaurant // get all the foods from a specific restaurant
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _selectedFood = mutableStateOf<FoodResponse?>(null)
    val selectedFood: State<FoodResponse?> = _selectedFood // get selected food
    // -----------------------------------------------------------------------------------------------------------------------------


    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        getAllRestaurants()
    }


    fun getAllRestaurants(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    restaurantRepository.getAllRestaurants()
                }
                _restaurants.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRestaurantById(restaurantId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    restaurantRepository.getRestaurantById(restaurantId)
                }
                _selectedRestaurant.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getFoodsByRestaurantId(restaurantId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    restaurantRepository.getFoodsByRestaurantId(restaurantId)
                }
                _foodsFromRestaurant.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getFoodById(foodId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    restaurantRepository.getFoodById(foodId)
                }
                _selectedFood.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    class Factory(private val restaurantRepository: RestaurantRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RestaurantViewModel(restaurantRepository) as T
        }
    }
}