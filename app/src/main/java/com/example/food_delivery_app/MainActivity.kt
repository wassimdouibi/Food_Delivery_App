package com.example.food_delivery_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_delivery_app.components.*
import com.example.food_delivery_app.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Food_Delivery_AppTheme {
//                val snackbarHostState = remember { SnackbarHostState() }
//                val scope = rememberCoroutineScope()
//                ObserveAsEvents(
//                    flow = SnackbarController.events,
//                    snackbarHostState
//                ) {
//                    event ->
//                    scope.launch {
//                        snackbarHostState.currentSnackbarData?.dismiss()
//
//                        val result = snackbarHostState.showSnackbar(
//                            message = event.message,
//                            actionLabel = event.action?.name,
//                            duration = SnackbarDuration.Short
//                        )
//
//                        if(result == SnackbarResult.ActionPerformed) {
//                            event.action?.action?.invoke()
//                        }
//                    }
//                }
                Scaffold(
//                    snackbarHost = {
//                        SnackbarHost(snackbarHostState)
//                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) {
                    innerPadding ->
                    NavigationScreen(
                       navController = rememberNavController(),
                    )
                }
            }
        }
    }
}


@Composable
fun NavigationScreen(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route
    ) {
        composable(Destination.Home.route) {
            Greeting(navController = navController)
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    navController: NavHostController
){

}





//@Composable
//fun Greeting(modifier: Modifier = Modifier, navController: NavHostController) {
//    val scope = rememberCoroutineScope()
//
//    Column(
//        modifier = modifier.padding(16.dp).fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ){
//        Button(
//            onClick = {
//                scope.launch {
//                    SnackbarController.sendEvent(
//                        event = SnackbarEvent(
//                            message = "Your password has been changed successfully, back to login"
//                        )
//                    )
//                }
//            }
//        ){
//            Text("Click Me")
//        }
//    }
//}


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
//    SegmentedButton(
//        modifier = modifier,
//        options = listOf("Ongoing", "History")
//    )
//  CustomRange(
////        modifier = modifier.fillMaxWidth(),
////        title = "Rating range"
////    )
//        Filter(
//            modifier = Modifier.fillMaxWidth(),
//            onDismiss = {
////                navController.popBackStack()
//            },
//            onRangeUpdated = { min, max -> /* handle updated range */ }
//        )