package com.example.food_delivery_app

import android.app.Application
import com.example.food_delivery_app.auth.Model.entity.AuthPreferences
import com.example.food_delivery_app.auth.Model.repository.AuthRepository
import com.example.food_delivery_app.auth.Model.service.AuthService
import com.example.food_delivery_app.core.home.model.repository.HomeRepository
import com.example.food_delivery_app.core.home.model.services.HomeService
import com.example.food_delivery_app.core.profile.model.repository.ProfileRepository
import com.example.food_delivery_app.core.profile.model.service.ProfileService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodDeliveryApplication : Application() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(NetworkModule.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    lateinit var authPreferences: AuthPreferences


    // Auth
    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
    val authRepository: AuthRepository by lazy {
        AuthRepository(authService)
    }

    // Profile
    val profileService: ProfileService by lazy {
        retrofit.create(ProfileService::class.java)
    }

    val profileRepository: ProfileRepository by lazy {
        ProfileRepository(profileService)
    }

    // Home
    val homeService: HomeService by lazy {
        retrofit.create(HomeService::class.java)
    }
    val homeRepository: HomeRepository by lazy {
        HomeRepository(homeService)
    }

//    // Orders
//    val ordersService: OrdersService by lazy {
//        retrofit.create(OrdersService::class.java)
//    }
//    val ordersRepository: OrdersRepository by lazy {
//        OrdersRepository(ordersService)
//    }
//
//    // Favorites
//    val favoritesService: FavoritesService by lazy {
//        retrofit.create(FavoritesService::class.java)
//    }
//    val favoritesRepository: FavoritesRepository by lazy {
//        FavoritesRepository(favoritesService)
//    }

    override fun onCreate() {
        super.onCreate()
        authPreferences = AuthPreferences(this)
    }
}