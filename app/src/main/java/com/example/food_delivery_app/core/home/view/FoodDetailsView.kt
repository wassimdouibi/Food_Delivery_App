package com.example.food_delivery_app.core

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.food_delivery_app.R
import com.example.food_delivery_app.auth.model.entity.AuthPreferences
import com.example.food_delivery_app.core.components.AddToCardOrder
import com.example.food_delivery_app.core.components.CustomerReview
import com.example.food_delivery_app.core.favorites.viewModel.FavoritesViewModel
import com.example.food_delivery_app.core.home.view.components.FoodCardImageSection
import com.example.food_delivery_app.core.home.view.components.SectionTitle
import com.example.food_delivery_app.core.home.viewModel.HomeViewModel
import com.example.food_delivery_app.core.profile.viewmodel.ProfileViewModel
import com.example.food_delivery_app.ui.theme.Colors.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomColorScheme
import com.example.food_delivery_app.ui.theme.LocalCustomTypographyScheme
import com.example.food_delivery_app.ui.theme.Typography.defaultCustomTypographyScheme
import kotlinx.coroutines.launch


val imageUrls = listOf(
    "https://images.unsplash.com/photo-1550547660-d9450f859349",
    "https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg",
    "https://images.pexels.com/photos/775031/pexels-photo-775031.jpeg",
    "https://images.pexels.com/photos/410648/pexels-photo-410648.jpeg",
    "https://images.pexels.com/photos/1279330/pexels-photo-1279330.jpeg",
    "https://images.pexels.com/photos/357756/pexels-photo-357756.jpeg"
)


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoodDetailsView(
    navController : NavController,
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel,
    favoritesViewModel: FavoritesViewModel,
    foodID : Int
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isLoading by homeViewModel.isLoading.collectAsState()
    val error by homeViewModel.error.collectAsState()
    val authPreferences = AuthPreferences(context)


    val selectedFood by homeViewModel.selectedFood.collectAsState()
    val selectedRestaurant by homeViewModel.selectedRestaurant.collectAsState()
    val restaurantReviews by homeViewModel.restaurantReviews.collectAsState()

    var isFavorite by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(0) }
    var isNoteVisible by remember { mutableStateOf(false) } // State for showing/hiding the input field
    var note by remember { mutableStateOf("") } // State for storing the note


    LaunchedEffect(1) {
        homeViewModel.getFoodById(foodID)
    }

    LaunchedEffect(selectedFood) {
        selectedFood?.let { foodRes ->
            homeViewModel.getRestaurantById(foodRes.food.restaurantId)
            homeViewModel.getRestaurantReviews(foodRes.food.restaurantId)
        }
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
        CircularProgressIndicator()
    } else {
        selectedFood?.let { foodRes ->
            Scaffold(
                bottomBar = {
                    AddToCardOrder(
                        price = foodRes.food.price.toFloat(),
                        initialValue = 1,
                        addOrder = {

                        },
                        modifier = Modifier
                            .background(Color.White)
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    )
                }
            ) {
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
                        FoodCardImageSection(pictures= imageUrls)

                        IconButton(
                            onClick = {
                                isFavorite = !isFavorite
                                coroutineScope.launch {
                                    if (userId != "-1") {
                                        favoritesViewModel.addFoodToFavorites(userId!!.toInt(), foodID)
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
                                tint = if (isFavorite) LocalCustomColorScheme.current.primary500 else LocalCustomColorScheme.current.ink300 // Change color based on state
                            )
                        }

                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(8.dp)
                                .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
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
                            // name section
                            Text(
                                text = foodRes.food.name,
                                style = defaultCustomTypographyScheme.heading3,
                                color = defaultCustomColorScheme.ink500
                            )

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
                                    text = selectedRestaurant!!.restaurant.address,
                                    style = defaultCustomTypographyScheme.p_smallBold.copy(fontFamily = FontFamily.Default),
                                    color = defaultCustomColorScheme.ink500
                                )
                            }
                        }

                        //  Description section
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SectionTitle(title = "Description")
                            Text(
                                text = foodRes.food.description ?: " No Description Found",
                                style = LocalCustomTypographyScheme.current.p_small,
                                color = LocalCustomColorScheme.current.ink400,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Justify
                            )
                        }


                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SectionTitle(title = "Customize Your Food")
                            Row(
                                modifier = Modifier.fillMaxWidth().height(48.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(LocalCustomColorScheme.current.primary100)
                                    .padding(horizontal = 14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Add Note",
                                    style = LocalCustomTypographyScheme.current.p_smallBold,
                                    color = LocalCustomColorScheme.current.ink500
                                )
                                IconButton(
                                    onClick = {
                                        // Toggle the visibility of the note input field
                                        isNoteVisible = !isNoteVisible
                                    },
                                    modifier = Modifier.border(0.dp, Color.Transparent, RectangleShape)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_plus),
                                        contentDescription = "plus icon",
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }

                            // If isNoteVisible is true, show the input field
                            if (isNoteVisible) {
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Want to customize your order, please add a note to notify us !",
                                    style = LocalCustomTypographyScheme.current.p_small,
                                    color = LocalCustomColorScheme.current.ink400
                                )
                                // Input field for the note with rounded corners
                                var isFocused by remember { mutableStateOf(false) }

                                OutlinedTextField(
                                    value = note,
                                    onValueChange = { note = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color.White)
                                        .focusable() // Track focus state
                                        .onFocusChanged {
                                            isFocused = it.isFocused // Update focus state
                                        },
                                    placeholder = { Text("Type your note here...") },
                                    maxLines = 5, // Allow multiple lines of text
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = LocalCustomColorScheme.current.primary200, // Set primary color when focused
                                        unfocusedBorderColor = LocalCustomColorScheme.current.primary100 // Set border color when not focused
                                    )
                                )
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

            }
        }
    }
}


@Composable
fun BottomBar(quantity: Int, foodPrice: Double, onQuantityChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Add a background color for clarity
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Quantity Controls
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Minus Icon

            IconButton(
                onClick = {
                    if (quantity > 0) onQuantityChange(quantity - 1)
                },
                enabled = quantity > 0,
                modifier = Modifier.border(0.dp, Color.Transparent, RectangleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = "minus icon",
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            // Spacer between minus and quantity

            // Quantity Text
            Text(
                text = "$quantity",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Spacer between quantity and plus

            // Plus Icon
            IconButton(
                onClick = {
                    onQuantityChange(quantity + 1)
                },
                modifier = Modifier.border(0.dp, Color.Transparent, RectangleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "plus icon",
                    modifier = Modifier
                        .size(30.dp)
                )
            }


        }

        // Spacer between Quantity Controls and Add to Cart Button
        Spacer(modifier = Modifier.width(16.dp))

        // Add to Cart Button
        Button(
            onClick = { /* Add to cart logic here */ },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9B66) // Set custom background color
            )
        ) {
            // Cart Text
            Text(text = "Add to Cart", modifier = Modifier.padding(end = 4.dp))

            // Cart Icon
            Image(
                painter = painterResource(id = R.drawable.orders),
                contentDescription = "Cart icon",
                modifier = Modifier.size(20.dp)
            )

            // Price Text
            Text(
                text = " ${quantity * foodPrice} DZD",
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}