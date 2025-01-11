//package com.example.food_delivery_app.core.Home
//
//import com.example.food_delivery_app.R
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.food_delivery_app.auth.ViewModel.AuthViewModel
//import com.example.food_delivery_app.core.Home.View.components.CuisineIcon
//import com.example.food_delivery_app.core.Home.View.components.SectionTitle
//import com.example.food_delivery_app.core.Restaurants.Model.entity.Restaurant
//import com.example.food_delivery_app.core.components.*
//import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
//import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme
//
///* ce qui reste : reasearch result page , which will be the result of the clicking
//* on the cuisine and categories cards as well as reasearch */
//
//
//@Composable
//fun HomeView(
//    navController: NavController,
//) {
//    val cuisines: List<Cuisine> = cuisinesList
//    val categories: List<Cuisine> = categoriesList
//    val restaurants: List<Restaurant> = listOf(restaurant1 , restaurant2, restaurant3)
//
//    var search by remember { mutableStateOf("") }
//    var showFilterState by rememberSaveable { mutableStateOf(false) }
//
//
//    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Search Bar
//        FoodDeliveryTextField(
//            value = search,
//            onValueChange = { search = it },
//            placeHolderText = stringResource(R.string.input_search_restaurant),
//            leadingIconVector = Icons.Default.Search,
//            trailingIconId = R.drawable.ri_equalizer_fill,
//            modifier = Modifier.fillMaxWidth(.9f).align(Alignment.CenterHorizontally),
//            changeShowFilterState = { showFilterState = !showFilterState},
//            keyboardActions = KeyboardActions(
//                onSearch = {
//                    navController.navigate("search_results/${search}") {
//                        // Navigate to search results screen
//                    }
//                }
//            ),
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Search
//            )
//        )
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Cuisines Section
//        SectionTitle(title = "Cuisines")
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            items(cuisines) { cuisine ->
//                CuisineIcon(
//                    cuisine = cuisine,
//                    onClick = {  }
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Top Categories Section
//        SectionTitle(title = "Top Categories")
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            items(categories) { category ->
//                CuisineIcon(
//                    cuisine = category,
//                    onClick = {  }
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Top Restaurants Section
//        SectionTitle(title = "Top Restaurants")
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        LazyRow (
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//
//            items(restaurants) { restaurant ->
//                RestaurantCard(
//                    restaurant = restaurant,
//                    navController = navController
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        SectionTitle(title = "Near Restaurants")
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        LazyRow (
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//
//            items(restaurants) { restaurant ->
//                RestaurantCard(
//                    restaurant = restaurant,
//                    navController = navController
//                )
//            }
//        }
//    }
//}
//
//
//
//
//
