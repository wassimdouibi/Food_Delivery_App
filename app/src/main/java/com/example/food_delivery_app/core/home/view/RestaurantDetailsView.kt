package com.example.food_delivery_app.core

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.model.entity.AuthPreferences
import com.example.food_delivery_app.core.components.CustomerReview
import com.example.food_delivery_app.core.components.FoodMenuCard
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.core.home.view.components.SectionTitle
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RestaurantDetailsView(
    navController : NavController,
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel,
    favoritesViewModel: FavoritesViewModel,
    restaurantId: Int
) {
    val context = LocalContext.current
    val isLoading by homeViewModel.isLoading.collectAsState()
    val error by homeViewModel.error.collectAsState()
    val authPreferences = AuthPreferences(context)
    val coroutineScope = rememberCoroutineScope()


    var isFavorite by remember { mutableStateOf(false) }
    val mealTypes = listOf("All", "Breakfast", "Lunch & Dinner", "Desserts", "Drinks")
    var selectedMealTypeIndex by remember { mutableStateOf(0) }
    val selectedRestaurant by homeViewModel.selectedRestaurant.collectAsState()
    val foodsFromRestaurant by homeViewModel.foodsFromRestaurant.collectAsState()
    val restaurantReviews by homeViewModel.restaurantReviews.collectAsState()


    LaunchedEffect(1) {
        homeViewModel.getRestaurantById(restaurantId)
        homeViewModel.getFoodsByRestaurantId(restaurantId)
        homeViewModel.getRestaurantReviews(restaurantId)
    }

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    var userId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(authPreferences) {
        authPreferences.userIdFlow.collect {
            userId = it
        }
    }

    if (isLoading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        selectedRestaurant?.let {
            restaurantRes ->
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_restaurant_1), // First image
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )

                        IconButton(
                            onClick = {
                                isFavorite = !isFavorite
                                coroutineScope.launch {
                                    if (userId != "-1") {
                                        favoritesViewModel.addRestaurantToFavorites(userId!!.toInt(), restaurantId)
                                    } else {
                                        Log.d("Add to favorite", "Error adding the food to favorites")
                                    }
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(LocalCustomColorScheme.current.primary100, shape = RoundedCornerShape(8.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) defaultCustomColorScheme.primary500 else defaultCustomColorScheme.ink300
                            )
                        }

                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(8.dp)
                                .background(LocalCustomColorScheme.current.primary100, shape = RoundedCornerShape(8.dp))
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = LocalCustomColorScheme.current.ink300
                            )
                        }

                    }

                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // name section
                                Text(
                                    text = restaurantRes.restaurant.name,
                                    style = defaultCustomTypographyScheme.heading3,
                                    color = defaultCustomColorScheme.ink500
                                )

                                Text(
                                    text = "More Info",
                                    style = defaultCustomTypographyScheme.p_mediumBold,
                                    color = defaultCustomColorScheme.ink400,
                                    modifier = Modifier.clickable { }  // add navigation to the restaurant details component
                                )
                            }

                            //location Section
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_location),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(34.dp)

                                )

                                Spacer(Modifier.width(8.dp))

                                Text(
                                    text = restaurantRes.restaurant.address,
                                    style = defaultCustomTypographyScheme.p_smallBold.copy(fontFamily = FontFamily.Default),
                                    color = defaultCustomColorScheme.ink500
                                )
                            }
                        }

                        // Known For Section
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SectionTitle(title = "Known For")
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),

                                ) {
                                restaurantRes.tags.forEachIndexed { index, pick ->
                                    Box(
                                        modifier = Modifier
                                            .background(Color.White)
                                            .border(width = 1.dp, color = defaultCustomColorScheme.ink300)
                                            .padding(horizontal = 16.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = pick,
                                            style = defaultCustomTypographyScheme.p_small,
                                            color = defaultCustomColorScheme.ink400
                                        )
                                    }
                                }
                            }
                        }


                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            //Categories section
                            SectionTitle(title = "Categories")

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp) // Equal spacing between items
                            ) {
                                items(mealTypes.size) { mealTypeIndex ->
                                    val isSelected = mealTypeIndex == selectedMealTypeIndex
                                    Text(
                                        text = mealTypes[mealTypeIndex],
                                        style = defaultCustomTypographyScheme.p_mediumBold,
                                        color = if (isSelected) defaultCustomColorScheme.primary400 else defaultCustomColorScheme.ink400,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .clickable {
                                                selectedMealTypeIndex = mealTypeIndex
                                            }
                                    )
                                }
                            }
                            // ************************** Food Section *******************************
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                val filteredFoods = when (selectedMealTypeIndex) {
                                    0 -> foodsFromRestaurant // "All" selected, show all food items
                                    else -> foodsFromRestaurant.filter {
                                        it.category.name.equals(mealTypes[selectedMealTypeIndex], ignoreCase = true)
                                    }
                                }
                                // Filter the food list based on the selected category
                                items(filteredFoods.size) {
                                        foodResponse -> FoodMenuCard(
                                    navController = navController,
                                    foodResponse = filteredFoods[foodResponse],
                                    restaurantResponse = selectedRestaurant!!
                                )
                                }
                            }
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SectionTitle(title = "Reviews")
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                restaurantReviews.forEach { review ->
                                    CustomerReview(
                                        profileViewModel = profileViewModel,
                                        customerReview = review
                                    )
                                }

                            }
                        }

                    }
                }
        } ?: run {
            Text("Restaurant not found")
        }
    }
}