package com.example.food_delivery_app

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.core.*
import com.example.food_delivery_app.ui.theme.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.food_delivery_app.auth.data.entity.AuthPreferences
import com.example.food_delivery_app.auth.data.entity.UserRepository
import com.example.food_delivery_app.auth.domain.AuthViewModel
import com.example.food_delivery_app.auth.presentation.components.ResetPasswordSuccessBox
import com.example.food_delivery_app.auth.presentation.login.view.Login
import com.example.food_delivery_app.core.Home.HomeScreen
import com.example.food_delivery_app.core.Home.PreviewHomeScreen
import com.example.food_delivery_app.core.profile.EditProfileView
import com.example.food_delivery_app.navigation.Navigation
import com.example.food_delivery_app.onboarding.presentation.Onboarding


class MainActivity : ComponentActivity() {
    private lateinit var authPreferences: AuthPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authPreferences = AuthPreferences(applicationContext)

        val authViewModel = AuthViewModel(
            repository = UserRepository(),
            authPreferences = authPreferences
        )

        // Request permissions after super.onCreate()
        requestNotificationPermission()
        enableEdgeToEdge()

        setContent {
            Food_Delivery_AppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { paddingValues ->  // Add paddingValues parameter
                    val context = LocalContext.current
                    val pref = context.getSharedPreferences("local", Context.MODE_PRIVATE)
                    //PreviewHomeScreen()
                    //PreviewHomeSearchResultScreen()
                    Navigation(
                        authViewModel = authViewModel,
                        pref = pref
                    )


                }
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }


//class MainActivity : ComponentActivity() {
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
//
//    @RequiresApi(34)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        requestNotificationPermission()
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContent {
//            Food_Delivery_AppTheme {
//                Scaffold(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
//                    val context = LocalContext.current
//                    val pref = context.getSharedPreferences("local", Context.MODE_PRIVATE)
//
//                    Navigation(
//                        authViewModel = viewModel(),
//                        pref = pref
//                    )


    // PreviewRestaurantCard()
    //PreviewHomeScreen()
    // PreviewCustomerReview()
    //previewRestDetailsScreen()
    /*Column( ){
                    BorderlessTextButton(
                        onClick = { /* Handle click action */ },
                        textContent = "Click Me"
                    )

                    FilledTextButton(
                        onClick = { /* Handle click action */ },
                        textContent = "Click Me"
                    )
                    GhostTextButton(
                        onClick = { /* Handle click action */ },
                        textContent = "Click Me"
                    )
                    RoundedIconBtn(
                        onClick = { /* Handle click action */ },
                        icon = Icons.Filled.Info,  // Use any icon you prefer
                        iconDescription = "info"
                    )
                    RoundedIconBtn(
                        onClick = { /* Handle click action */ },
                        icon = Icons.Filled.Info,  // Use any icon you prefer
                        iconDescription = "Search",
                        contentColor = Color.White,   // Change text color
                        containerColor = Color.Blue  // Change button background color
                    )
                    SquareIconButton(
                        onClick = { /* Handle click action */ },
                        icon = Icons.Filled.Info,  // Use any icon you prefer
                        iconDescription = "info"
                    )

                    FilledTextButtonWithIcon(
                        onClick = { /* Handle click action */ },
                        textContent = "Details",
                        icon = Icons.Filled.Info,  // Use any icon you prefer
                        iconDescription = "info"

                    )
                }*/


//                }
//            }
//        }
//    }
//}


    //                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .systemBarsPadding()
//                            .padding(horizontal = 16.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ){
//                        OrderConfirmed(
//                            orderLoc = "Warehouse A",
//                            deliveryLoc = "Customer Address",
//                            price = 1234.56f,
//
//                            onButton1Click = {},
//                            onButton2Click = {},
//
//                            cardColor = LocalCustomColorScheme.current.utilityWhiteBackground
//                        )
//
//                        OrderDelivered(
//                            orderLoc = "Warehouse A",
//                            deliveryLoc = "Customer Address",
//                            price = 1234.56f,
//
//                            cardColor = LocalCustomColorScheme.current.utilityWhiteBackground
//                        )
//
//                        OrderNotConfirmed(
//                            price = 1234.56f,
//                            onButton1Click = {},
//                            onButton2Click = {}
//                        )
//                        EditItem(
//                            text= "Add an item",
//                            onClick={},
//                            cardColor = LocalCustomColorScheme.current.primary100
//                        )
//                        ShopItem(
//                           price=98.2f,
//                           onClick = {},
//                            cardColor = LocalCustomColorScheme.current.primary400
//                        )
//                        AddToCardOrder(
//                            price=48f,
//                            initialValue = 0 ,
//                            onBtnTextLessClick={}
//                        )
//                        ItemCard(
//                            itemName = "Burger",
//                            price = 500f,
//                            imageRes = R.drawable.pizza, // Image dans le répertoire drawable
//                            noteTitle = "Note ",
//                            noteContent = "le note xxxxxx", // Ou une chaîne de caractères non vide pour tester
//                            onDeleteClick = { /* Handle item deletion */ },
//                            onBtnTextLessClick = { /* Handle TextLess button click */ },
//                            initialValue = 1, // Valeur initiale pour l'IncrementDecrementRow
//                            modifier = Modifier.padding(16.dp)
//                        )
//                        ItemCardEmptyNote(
//                            itemName = "Burger",
//                            price = 500f,
//                            imageRes = R.drawable.pizza, // Image dans le répertoire drawable
//                            onDeleteClick = { /* Handle item deletion */ },
//                            onBtnTextLessClick = { /* Handle TextLess button click */ },
//                            initialValue = 1, // Valeur initiale pour l'IncrementDecrementRow
//                            modifier = Modifier.padding(16.dp)
//                        )
//                    }
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Column {
//        SquareIconButton(
//            onClick = {},
//            modifier = modifier,
//            icon = Icons.Filled.Add,
//            iconDescription = "Add",
//            buttonSize = ButtonSize.LARGE
//        )
//        FilledTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true
//        )
//        FilledTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true,
//            enabled = false
//        )
//        GhostTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true
//        )
//        BorderlessTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true,
//        )
//        BorderlessTextButton(
//            onClick = {},
//            modifier = modifier,
//            textContent = "Log in",
//            buttonSize = ButtonSize.LARGE,
//            isPLargeBold = true,
//            enabled = false
//        )
//        RoundedIconBtn(
//            onClick = {},
//            modifier = modifier,
//            icon = Icons.Filled.Delete,
//            iconDescription = "Delete",
//            contentColor = LocalCustomColorScheme.current.ink50,
//            containerColor = LocalCustomColorScheme.current.utilityError
//        )
//        RoundedIconBtn(
//            onClick = {},
//            modifier = modifier,
//            icon = Icons.Filled.Delete,
//            iconDescription = "Delete",
//            contentColor = LocalCustomColorScheme.current.ink50,
//            containerColor = LocalCustomColorScheme.current.utilityError,
//            enabled = false
//        )
//    }
    }

        @Composable
        fun GreetingPreview() {
            Food_Delivery_AppTheme {
                Greeting("Android")
            }
        }

        @Composable
        fun Navigation2(navController: NavHostController) {

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(
                        navController = navController
                    )
                }
                composable(
                    "restaurant_details/{restaurantId}",
                    arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val restaurantId = backStackEntry.arguments?.getInt("restaurantId")
                    RestaurantDetailsScreen(navController, restaurantId ?: 1 )
                }
                composable("restdetail") {
                    RestaurantDetailsScreen(
                        navController = navController, 1
                    )
                }
            }
        }
    }
