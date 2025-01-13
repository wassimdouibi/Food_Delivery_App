package com.example.food_delivery_app.core.favorites.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.core.favorites.model.repository.FavoritesRepository
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteFoodRequest
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteRestaurantRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(val favoritesRepository: FavoritesRepository): ViewModel() {

    private val _favoriteRestaurants = MutableStateFlow<List<RestaurantResponse>>(emptyList())
    val favoriteRestaurants: StateFlow<List<RestaurantResponse>> = _favoriteRestaurants
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _favoriteFoods = MutableStateFlow<List<FoodResponse>>(emptyList())
    val favoriteFoods: StateFlow<List<FoodResponse>> = _favoriteFoods
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _addFavoriteFoodRequestStatus = MutableStateFlow<Int?>(null)
    val addFavoriteFoodRequestStatus: StateFlow<Int?> = _addFavoriteFoodRequestStatus
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _addFavoriteRestaurantRequestStatus = MutableStateFlow<Int?>(null)
    val addFavoriteRestaurantRequestStatus: StateFlow<Int?> = _addFavoriteRestaurantRequestStatus


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    fun getFavoriteRestaurants(userId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    favoritesRepository.getFavoriteRestaurants(userId)
                }
                _favoriteRestaurants.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getFavoriteFoods(userId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    favoritesRepository.getFavoriteFoods(userId)
                }
                _favoriteFoods.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addFoodToFavorites(userId: Int, menuId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    favoritesRepository.addFoodToFavorites(
                        AddFavoriteFoodRequest(
                            userId = userId,
                            menuId = menuId
                        )
                    )
                }
                _addFavoriteFoodRequestStatus.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addRestaurantToFavorites(userId: Int, restaurantId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    favoritesRepository.addRestaurantToFavorites(
                        AddFavoriteRestaurantRequest(
                            userId = userId,
                            restaurantId = restaurantId
                        )
                    )
                }
                _addFavoriteRestaurantRequestStatus.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    class Factory(private val favoritesRepository: FavoritesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavoritesViewModel(favoritesRepository) as T
        }
    }
}