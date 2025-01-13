package com.example.food_delivery_app.core.home.model.services

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.core.home.model.entity.Category
import com.example.food_delivery_app.core.home.model.entity.CuisineType
import com.example.food_delivery_app.core.home.model.entity.Restaurant
import com.example.food_delivery_app.core.home.model.entity.Review
import com.example.food_delivery_app.core.home.model.services.response.FoodResponse
import com.example.food_delivery_app.core.home.model.services.response.RestaurantResponse
import com.example.food_delivery_app.core.profile.model.service.ProfileService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("menus/categories")
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
    suspend fun getRestaurantById(@Path("id") restaurantId: Int): Response<RestaurantResponse>

    @GET("menus/restaurant/{id}")
    suspend fun getFoodsByRestaurantId(@Path("id") restaurantId: Int) : List<FoodResponse>

    @GET("menus/{id}")
    suspend fun getFoodById(@Path("restaurantId") foodID: Int) : Response<FoodResponse>

    @GET("restaurants/reviews/{restaurantId}")
    suspend fun getRestaurantReviews(@Path("restaurantId") restaurantId: Int) : Response<List<Review>>

    companion object {
        private var homeInterface: HomeService? = null
        fun getInstance(): HomeService {
            if (homeInterface == null) {
                homeInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(HomeService::class.java)
            }
            return homeInterface!!
        }
    }
}