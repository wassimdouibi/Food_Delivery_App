package com.example.food_delivery_app.core.home.model.services

import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("restaurants/categories")
    suspend fun getCategories(): List<Category>

    @GET ("restaurants/cuisinetypes")
    suspend fun getCuisineTypes(): List<CuisineType>

    @GET("restaurants/filter")
    suspend fun getRestaurantsCuisineType(cuisineType: CuisineType): List<RestaurantResponse>

    @GET("menus/filter")
    suspend fun getFoodsFromSpecificCategory(foodCategory: Category): List<FoodResponse>

    @GET("restaurants")
    suspend fun getAllRestaurants(): Response<List<RestaurantResponse>>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("restaurantId") restaurantId: Int) : Response<RestaurantResponse>

    @GET("menus/restaurant/{id}")
    suspend fun getFoodsByRestaurantId(@Path("restaurantId") restaurantId: Int) : Response<List<FoodResponse>>

    @GET("menus/{id}")
    suspend fun getFoodById(@Path("restaurantId") foodID: Int) : Response<FoodResponse>
}