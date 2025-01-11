package com.example.food_delivery_app.core.favorites.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import com.example.food_delivery_app.core.favorites.model.repository.FavoritesRepository
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteFoodRequest
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteRestaurantRequest
import com.example.food_delivery_app.core.favorites.model.service.response.AddFavoriteFoodResponse
import com.example.food_delivery_app.core.favorites.model.service.response.AddFavoriteRestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(val favoritesRepository: FavoritesRepository): ViewModel() {

    private val _favoriteRestaurants = mutableStateOf<List<RestaurantResponse>>(emptyList())
    val favoriteRestaurants: State<List<RestaurantResponse>> = _favoriteRestaurants
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _favoriteFoods = mutableStateOf<List<FoodResponse>>(emptyList())
    val favoriteFoods: State<List<FoodResponse>> = _favoriteFoods
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _addFavoriteFoodRequestStatus = mutableStateOf<AddFavoriteFoodResponse?>(null)
    val addFavoriteFoodRequestStatus: State<AddFavoriteFoodResponse?> = _addFavoriteFoodRequestStatus
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _addFavoriteRestaurantRequestStatus = mutableStateOf<AddFavoriteRestaurantResponse?>(null)
    val addFavoriteRestaurantRequestStatus: State<AddFavoriteRestaurantResponse?> = _addFavoriteRestaurantRequestStatus


    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error


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