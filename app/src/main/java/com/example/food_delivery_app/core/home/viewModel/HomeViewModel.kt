package com.example.food_delivery_app.core.home.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.repository.HomeRepository
import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(val homeRepository: HomeRepository): ViewModel() {
    // Get All Food Categories
    private val _foodCategories = mutableStateOf<List<Category>>(emptyList())
    val foodCategories: State<List<Category>> = _foodCategories
    // -----------------------------------------------------------------------------------------------------------------------------
    // Get All Cuisine Types
    private val _cuisineTypes = mutableStateOf<List<CuisineType>>(emptyList())
    val cuisineTypes: State<List<CuisineType>> = _cuisineTypes
    // -----------------------------------------------------------------------------------------------------------------------------
    // Get All Restaurants of that Cuisine Type
    private val _restaurantsFromSpecificCuisineType = mutableStateOf<List<RestaurantResponse>>(emptyList())
    val restaurantsFromSpecificCuisineType: State<List<RestaurantResponse>> = _restaurantsFromSpecificCuisineType
    // -----------------------------------------------------------------------------------------------------------------------------
    // Get All Foods of a specific category
    private val _foodsFromSpecificCategory = mutableStateOf<List<FoodResponse?>>(emptyList())
    val foodsFromSpecificCategory: State<List<FoodResponse?>> = _foodsFromSpecificCategory



    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error



    fun getCategories(){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getCategories()
                }
                _foodCategories.value = response
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
                _cuisineTypes.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRestaurantsOfCuisineType(cuisineType: CuisineType){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getRestaurantsCuisineType(cuisineType)
                }
                _restaurantsFromSpecificCuisineType.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getFoodsFromSpecificCategory(foodCategory: Category){
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = withContext(Dispatchers.IO) {
                    homeRepository.getFoodsFromSpecificCategory(foodCategory)
                }
                _foodsFromSpecificCategory.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
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