package com.example.food_delivery_app.core.restaurants.model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RestaurantService {
    @GET("restaurants")
    suspend fun getAllRestaurants(): Response<List<RestaurantResponse>>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("restaurantId") restaurantId: Int) : Response<RestaurantResponse>

    @GET("menus/restaurant/{id}")
    suspend fun getFoodsByRestaurantId(@Path("restaurantId") restaurantId: Int) : Response<List<FoodResponse>>

    @GET("menus/{id}")
    suspend fun getFoodById(@Path("restaurantId") foodID: Int) : Response<FoodResponse>
}