package com.example.food_delivery_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Food_Delivery_AppTheme {
                Navigation(rememberNavController())
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
                //PreviewRestaurantInfo()
                /*Scaffold(

                    bottomBar = {
                        BottomBar( rememberNavController())
                }
                ){

                }*/
            }
        }
    }
}




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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Food_Delivery_AppTheme {
        Greeting("Android")
    }
}

@Composable
fun Navigation(navController : NavHostController){

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                cuisines = cuisinesList,
                categories = categoriesList,
                restaurants = listOf(restaurant1, restaurant2, restaurant3),
                navController = navController
            )
        }
        composable(
            "restaurant_details/{restaurantId}",
            arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId")
            RestaurantDetailsScreen(navController , restaurant1)
        }
        composable("restdetail") {
            RestaurantDetailsScreen(
                navController = navController, restaurant1
            )
        }
    }
}