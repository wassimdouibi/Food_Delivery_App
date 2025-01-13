package com.example.food_delivery_app.core.favorites.model.repository

import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.core.favorites.model.service.FavoritesService
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteFoodRequest
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteRestaurantRequest

class FavoritesRepository(private val favoritesService: FavoritesService) {
    suspend fun getFavoriteRestaurants(userId: Int): List<RestaurantResponse> {
        return try {
            val response = favoritesService.getFavoriteRestaurants(userId)
            response.ifEmpty {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception("Error getFavoriteRestaurants: ${e.message}")
        }
    }

    suspend fun addRestaurantToFavorites(addFavoriteRestaurantRequest: AddFavoriteRestaurantRequest): Int {
        return try {
            favoritesService.addRestaurantToFavorites(addFavoriteRestaurantRequest)
        } catch (e: Exception) {
            throw Exception("Failed to add restaurant to favorites: ${e.message}")
        }
    }

    suspend fun getFavoriteFoods(userId: Int): List<FoodResponse> {
        return try {
            val response = favoritesService.getFavoriteFoods(userId)
            response.ifEmpty {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception("Error getFavoriteFoods: ${e.message}")
        }
    }

    suspend fun addFoodToFavorites(addFavoriteFoodRequest: AddFavoriteFoodRequest): Int {
        return try {
            favoritesService.addFoodToFavorites(addFavoriteFoodRequest)
        } catch (e: Exception) {
            throw Exception("Failed to add food to favorites: ${e.message}")
        }
    }
}