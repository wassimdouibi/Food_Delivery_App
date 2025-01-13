package com.example.food_delivery_app

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.food_delivery_app.ui.theme.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.auth.viewModel.AuthViewModel
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.router.NavigationHost
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel


class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModel.Factory(
            (application as FoodDeliveryApplication).authRepository,
            (application as FoodDeliveryApplication).authPreferences
        )
    }
    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModel.Factory(
            (application as FoodDeliveryApplication).profileRepository
        )
    }
    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory(
            (application as FoodDeliveryApplication).homeRepository
        )
    }
    private val favoritesViewModel: FavoritesViewModel by viewModels {
        FavoritesViewModel.Factory(
            (application as FoodDeliveryApplication).favoritesRepository
        )
    }

//    private val ordersViewModel: OrdersViewModel by viewModels {
//        OrdersViewModel.Factory(
//            (application as FoodDeliveryApplication).ordersRepository
//        )
//    }


//    private fun requestNotificationPermission() {
//        if (Build.VERSION.SDK_INT > -Build.VERSION_CODES.TIRAMISU) {
//            val hasPermission = ContextCompat.checkSelfPermission(
//                this, android.Manifest.permission.POST_NOTIFICATIONS
//            ) == PackageManager.PERMISSION_GRANTED
//            if (!hasPermission) {
//                ActivityCompat.requestPermissions(
//                    this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0
//                )
//            }
//        }
//    }

    @RequiresApi(34)
    override fun onCreate(savedInstanceState: Bundle?) {
//        requestNotificationPermission()
        super.onCreate(savedInstanceState)

        setContent {
            Food_Delivery_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val context = LocalContext.current
                    val pref = context.getSharedPreferences("local_food_delivery_data", Context.MODE_PRIVATE)

                    NavigationHost(
                        navController = navController,
                        authViewModel = authViewModel,
                        profileViewModel = profileViewModel,
                        homeViewModel = homeViewModel,
                        favoritesViewModel = favoritesViewModel,
//                        ordersViewModel = ordersViewModel,
                        pref = pref
                    )

                }
            }
        }
    }
}