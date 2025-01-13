package com.example.food_delivery_app.core.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.entity.Review
import com.example.food_delivery_app.core.home.model.repository.HomeRepository
import com.example.food_delivery_app.core.home.model.services.request.FoodFilterRequest
import com.example.food_delivery_app.core.home.model.services.request.RestaurantFilterRequest
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val homeRepository: HomeRepository): ViewModel() {
    // Get All Food Categories
    private val _foodCategories = MutableStateFlow<List<Category>>(emptyList())
    val foodCategories: StateFlow<List<Category>> = _foodCategories.asStateFlow()
    // -----------------------------------------------------------------------------------------------------------------------------
    // Get All Cuisine Types
    private val _cuisineTypes = MutableStateFlow<List<CuisineType>>(emptyList())
    val cuisineTypes: StateFlow<List<CuisineType>> = _cuisineTypes
    // -----------------------------------------------------------------------------------------------------------------------------
    // Get All Restaurants of that Cuisine Type
    private val _restaurantsFromSpecificCuisineType = MutableStateFlow<List<RestaurantResponse>>(emptyList())
    val restaurantsFromSpecificCuisineType: StateFlow<List<RestaurantResponse>> = _restaurantsFromSpecificCuisineType
    // -----------------------------------------------------------------------------------------------------------------------------
    // Get All Foods of a specific category
    private val _foodsFromSpecificCategory = MutableStateFlow<List<FoodResponse?>>(emptyList())
    val foodsFromSpecificCategory: StateFlow<List<FoodResponse?>> = _foodsFromSpecificCategory
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _restaurants = MutableStateFlow<List<RestaurantResponse>>(emptyList())
    val restaurants: StateFlow<List<RestaurantResponse>> = _restaurants
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _selectedRestaurant = MutableStateFlow<RestaurantResponse?>(null)
    val selectedRestaurant: StateFlow<RestaurantResponse?> = _selectedRestaurant // get selected restaurant
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _foodsFromRestaurant = MutableStateFlow<List<FoodResponse>>(emptyList())
    val foodsFromRestaurant: StateFlow<List<FoodResponse>> = _foodsFromRestaurant // get all the foods from a specific restaurant
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _selectedFood = MutableStateFlow<FoodResponse?>(null)
    val selectedFood: StateFlow<FoodResponse?> = _selectedFood // get selected food
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _restaurantReviews = MutableStateFlow<List<Review>>(emptyList())
    val restaurantReviews: StateFlow<List<Review>> = _restaurantReviews // get restaurant reviews
    // -----------------------------------------------------------------------------------------------------------------------------


    // -----------------------------------------------------------------------------------------------------------------------------
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    // -----------------------------------------------------------------------------------------------------------------------------
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    // -----------------------------------------------------------------------------------------------------------------------------



    fun getCategories(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getCategories()
                }
                _foodCategories.emit(response)
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCuisineTypes(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getCuisineTypes()
                }
                _cuisineTypes.emit(response)
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRestaurantsOfCuisineType(restaurantFilterRequest: RestaurantFilterRequest){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getRestaurantsCuisineType(restaurantFilterRequest)
                }
                _restaurantsFromSpecificCuisineType.emit(response)
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getFoodsFromSpecificCategory(foodFilterRequest: FoodFilterRequest){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getFoodsFromSpecificCategory(foodFilterRequest)
                }
                _foodsFromSpecificCategory.emit(response)
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            }
        }
    }

    fun getAllRestaurants(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getAllRestaurants()
                }
                Log.d("Heeere", "Reponse restaurants selected : $response")
                _restaurants.emit(response)
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
                    homeRepository.getRestaurantById(restaurantId)
                }
                _selectedRestaurant.emit(response)
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
                    homeRepository.getFoodsByRestaurantId(restaurantId)
                }
                _foodsFromRestaurant.emit(response)
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
                    homeRepository.getFoodById(foodId)
                }
                _selectedFood.emit(response)
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRestaurantReviews(restaurantId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getRestaurantReviews(restaurantId)
                }
                _restaurantReviews.emit(response)
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }



    class Factory(private val homeRepository: HomeRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(homeRepository) as T
        }
    }

}