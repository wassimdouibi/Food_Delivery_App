package com.example.food_delivery_app.core.favorites.model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteFoodRequest
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteRestaurantRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoritesService {
    @GET("restaurants/favorite-restaurants/{userId}")
    suspend fun getFavoriteRestaurants(@Path("userId") userId: Int): List<RestaurantResponse>

    @GET("menus/favorite-menus/{userId}")
    suspend fun getFavoriteFoods(@Path("userId") userId: Int): List<FoodResponse>

    @POST("restaurants/add-favorite-restaurant")
    suspend fun addRestaurantToFavorites(@Body restaurantRequest: AddFavoriteRestaurantRequest): Int

    @POST("menus/add-favorite-menu")
    suspend fun addFoodToFavorites(@Body foodRequest: AddFavoriteFoodRequest): Int


    companion object {
        private var favoritesInterface: FavoritesService? = null
        fun getInstance(): FavoritesService {
            if (favoritesInterface == null) {
                favoritesInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(FavoritesService::class.java)
            }
            return favoritesInterface!!
        }
    }
}