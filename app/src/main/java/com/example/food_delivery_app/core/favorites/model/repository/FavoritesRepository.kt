package com.example.food_delivery_app.core.favorites.model.repository

import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import com.example.food_delivery_app.core.favorites.model.service.FavoritesService
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteFoodRequest
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteRestaurantRequest
import com.example.food_delivery_app.core.favorites.model.service.response.AddFavoriteFoodResponse
import com.example.food_delivery_app.core.favorites.model.service.response.AddFavoriteRestaurantResponse

class FavoritesRepository(private val favoritesService: FavoritesService) {
    suspend fun getFavoriteRestaurants(userId: Int): List<RestaurantResponse> {
        val response = favoritesService.getFavoriteRestaurants(userId)
        if(response.isSuccessful){
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching restaurants: ${response.errorBody()?.string()}")
        }
    }

    suspend fun addRestaurantToFavorites(addFavoriteRestaurantRequest: AddFavoriteRestaurantRequest): AddFavoriteRestaurantResponse {
        val response = favoritesService.addRestaurantToFavorites(addFavoriteRestaurantRequest)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Invalid response")
        } else {
            throw Exception("Failed to add food to favorites: ${response.message()}")
        }
    }

    suspend fun getFavoriteFoods(userId: Int): List<FoodResponse> {
        val response = favoritesService.getFavoriteFoods(userId)
        if(response.isSuccessful){
            return response.body() ?: emptyList()
        } else {
            throw Exception("Error fetching restaurants: ${response.errorBody()?.string()}")
        }
    }

    suspend fun addFoodToFavorites(addFavoriteFoodRequest: AddFavoriteFoodRequest): AddFavoriteFoodResponse {
        val response = favoritesService.addFoodToFavorites(addFavoriteFoodRequest)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Invalid response")
        } else {
            throw Exception("Failed to add food to favorites: ${response.message()}")
        }
    }
}