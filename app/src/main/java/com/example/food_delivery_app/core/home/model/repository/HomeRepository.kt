package com.example.food_delivery_app.core.home.model.repository

import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.services.HomeService
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse

class HomeRepository(private val homeService: HomeService) {
    suspend fun getCategories(): List<Category> {
        return try {
            val response = homeService.getCategories()
            response.ifEmpty {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception("Error fetching categories: ${e.message}")
        }
    }

    suspend fun getCuisineTypes(): List<CuisineType> {
        return try {
            val response = homeService.getCuisineTypes()
            response.ifEmpty {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception("Error fetching cuisine types: ${e.message}")
        }
    }

    suspend fun getRestaurantsCuisineType(cuisineType: CuisineType): List<RestaurantResponse> {
        return try {
            val response = homeService.getRestaurantsCuisineType(cuisineType)
            response.ifEmpty {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception("Error fetching restaurants of a specific cuisine type ${e.message}")
        }
    }

    suspend fun getFoodsFromSpecificCategory(foodCategory: Category): List<FoodResponse> {
        return try {
            val response = homeService.getFoodsFromSpecificCategory(foodCategory)
            response.ifEmpty {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception("Error fetching foods of a specific category: ${e.message}")
        }
    }

    suspend fun getAllRestaurants(): List<RestaurantResponse> {
        val response = homeService.getAllRestaurants()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching restaurants: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getRestaurantById(restaurantId: Int): RestaurantResponse {
        val response = homeService.getRestaurantById(restaurantId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Restaurant not found")
        } else {
            throw Exception("Error fetching restaurant: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getFoodsByRestaurantId(restaurantId: Int): List<FoodResponse> {
        val response = homeService.getFoodsByRestaurantId(restaurantId)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching foods: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getFoodById(foodId: Int): FoodResponse {
        val response = homeService.getFoodById(foodId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Food not found")
        } else {
            throw Exception("Error fetching food: ${response.errorBody()?.string()}")
        }
    }

}