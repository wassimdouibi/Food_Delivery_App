package com.example.food_delivery_app

import android.app.Application
import android.util.Log
import com.example.food_delivery_app.auth.model.entity.AuthPreferences
import com.example.food_delivery_app.auth.model.repository.AuthRepository
import com.example.food_delivery_app.auth.model.service.AuthService
import com.example.food_delivery_app.core.favorites.model.repository.FavoritesRepository
import com.example.food_delivery_app.core.favorites.model.service.FavoritesService
import com.example.food_delivery_app.core.home.model.repository.HomeRepository
import com.example.food_delivery_app.core.home.model.services.HomeService
import com.example.food_delivery_app.core.orders.model.repository.OrdersRepository
import com.example.food_delivery_app.core.orders.model.service.OrdersService
import com.example.food_delivery_app.core.profile.model.repository.ProfileRepository
import com.example.food_delivery_app.core.profile.model.service.ProfileService

class FoodDeliveryApplication : Application() {
    lateinit var authPreferences: AuthPreferences


    // Auth
    private val authService: AuthService by lazy { AuthService.getInstance() }
    val authRepository: AuthRepository by lazy { AuthRepository(authService) }

    // Profile
    private val profileService: ProfileService by lazy { ProfileService.getInstance() }
    val profileRepository: ProfileRepository by lazy { ProfileRepository(profileService) }

    // Home
    private val homeService: HomeService by lazy { HomeService.getInstance() }
    val homeRepository: HomeRepository by lazy { HomeRepository(homeService) }

//    // Orders
    val ordersService: OrdersService by lazy { OrdersService.getInstance() }
    val ordersRepository: OrdersRepository by lazy { OrdersRepository(ordersService) }


   // Favorites
    val favoritesService: FavoritesService by lazy { FavoritesService.getInstance() }
    val favoritesRepository: FavoritesRepository by lazy { FavoritesRepository(favoritesService) }

    override fun onCreate() {
        super.onCreate()
        authPreferences = AuthPreferences(applicationContext)
    }
}