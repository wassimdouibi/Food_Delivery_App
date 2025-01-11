package com.example.food_delivery_app.core.restaurants.model.repository

import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import com.example.food_delivery_app.core.restaurants.model.service.RestaurantService


class RestaurantRepository(private val restaurantService: RestaurantService) {
    suspend fun getAllRestaurants(): List<RestaurantResponse> {
        val response = restaurantService.getAllRestaurants()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching restaurants: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getRestaurantById(restaurantId: Int): RestaurantResponse {
        val response = restaurantService.getRestaurantById(restaurantId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Restaurant not found")
        } else {
            throw Exception("Error fetching restaurant: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getFoodsByRestaurantId(restaurantId: Int): List<FoodResponse> {
        val response = restaurantService.getFoodsByRestaurantId(restaurantId)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching foods: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getFoodById(foodId: Int): FoodResponse {
        val response = restaurantService.getFoodById(foodId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Food not found")
        } else {
            throw Exception("Error fetching food: ${response.errorBody()?.string()}")
        }
    }
}