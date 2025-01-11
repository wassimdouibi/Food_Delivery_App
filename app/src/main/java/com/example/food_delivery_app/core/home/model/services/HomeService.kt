package com.example.food_delivery_app.core.home.model.services

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.restaurants.model.service.response.FoodResponse
import com.example.food_delivery_app.core.restaurants.model.service.response.RestaurantResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HomeService {
    @GET("restaurants/categories")
    suspend fun getCategories(): List<Category>

    @GET ("restaurants/cuisinetypes")
    suspend fun getCuisineTypes(): List<CuisineType>

    @GET("restaurants/filter")
    suspend fun getRestaurantsCuisineType(cuisineType: CuisineType): List<RestaurantResponse>

    @GET("menus/filter")
    suspend fun getFoodsFromSpecificCategory(foodCategory: Category): List<FoodResponse>
}