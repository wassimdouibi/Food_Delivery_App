package com.example.food_delivery_app.core.favorites.model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteFoodRequest
import com.example.food_delivery_app.core.favorites.model.service.request.AddFavoriteRestaurantRequest
import com.example.food_delivery_app.core.favorites.model.service.response.AddFavoriteFoodResponse
import com.example.food_delivery_app.core.favorites.model.service.response.AddFavoriteRestaurantResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoritesService {
    @GET("restaurants/favorite-restaurants/{userId}")
    suspend fun getFavoriteRestaurants(@Path("userId") userId: Int): Response<List<RestaurantResponse>>

    @GET("menus/favorite-menus/{userId}")
    suspend fun getFavoriteFoods(@Path("userId") userId: Int): Response<List<FoodResponse>>

    @POST("restaurants/add-favorite-restaurant")
    suspend fun addRestaurantToFavorites(@Body restaurantRequest: AddFavoriteRestaurantRequest): Response<AddFavoriteRestaurantResponse>

    @POST("menus/add-favorite-menu")
    suspend fun addFoodToFavorites(@Body foodRequest: AddFavoriteFoodRequest): Response<AddFavoriteFoodResponse>
}