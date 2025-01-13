package com.example.food_delivery_app.core

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.food_delivery_app.R
import com.example.food_delivery_app.components.CardImageSection
import com.example.food_delivery_app.components.CustomerReview
import com.example.food_delivery_app.components.FoodDeliveryTextField
import com.example.food_delivery_app.components.FoodMenuCard
import com.example.food_delivery_app.components.InfoRow
import com.example.food_delivery_app.components.PreviewCustomerReview
import com.example.food_delivery_app.components.Restaurant
import com.example.food_delivery_app.components.RestaurantCard
import com.example.food_delivery_app.components.restaurant1
import com.example.food_delivery_app.core.Home.SectionTitle
import com.example.food_delivery_app.ui.theme.defaultCustomColorScheme
import com.example.food_delivery_app.ui.theme.defaultCustomTypographyScheme



/* ce qui reste :  get the Food using API*/

/*Classes Just like in the Backend*/

data class Menu(
    val menuId: Int,
    val restaurantId: Int,
    val name: String,
    val description: String?,
    val price: Double,
    val category: Int,
    val photo: String?,
)
val menu1 = Menu(
    menuId = 1,
    restaurantId = 101,
    name = "Margherita Pizza",
    description = "Classic pizza topped with fresh tomatoes, mozzarella, and basil.",
    price = 9.99,
    category = 1,
    photo = "https://example.com/photos/margherita_pizza.jpg"
)

data class Restau(
    val restaurantId: Int,
    val name: String,
    val logo: String,
    val address: String,
    val cuisineType: Int,
    val averageRating: Float,
    val reviewCount: Int,
    val contactPhone: String,
    val contactEmail: String,
    val fbLink: String?,
    val instaLink: String?
)
val restau = Restau(
    restaurantId = 101,
    name = "Gourmet Bites",
    logo = "https://example.com/logos/gourmet_bites.jpg",
    address = "123 Culinary Lane, Food City, FC 45678",
    cuisineType = 2,
    averageRating = 4.5f,
    reviewCount = 234,
    contactPhone = "+1234567890",
    contactEmail = "contact@gourmetbites.com",
    fbLink = "https://facebook.com/gourmetbites",
    instaLink = "https://instagram.com/gourmetbites"
)

val listReviews = listOf(
    CustomerReview(
        id = 1,
        title = "Amazing Pizza!",
        customerUsername = "foodie123",
        text = "The Margherita Pizza was absolutely delicious. The crust was perfectly baked, and the fresh basil added so much flavor. Highly recommend!",
        rating = 5
    ),
    CustomerReview(
        id = 2,
        title = "Good Burger",
        customerUsername = "burgerlover",
        text = "The cheeseburger was juicy and flavorful, but the bun could have been fresher. Overall, a good experience.",
        rating = 4
    ),
    CustomerReview(
        id = 3,
        title = "Refreshing Salad",
        customerUsername = "healthyeats",
        text = "Loved the Caesar Salad! The dressing was spot on, and the croutons were crispy. A bit pricey for the portion size though.",
        rating = 4
    ),
    CustomerReview(
        id = 4,
        title = "Delicious Spaghetti",
        customerUsername = "pastaenthusiast",
        text = "The Spaghetti Bolognese was packed with flavor and the portion was generous. Definitely worth it!",
        rating = 5
    )
)

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
fun FoodDetailsScreen(
    navController : NavController,
    restaurantID: Int,
    foodID : Int
) {
    val restaurant: Restau = restau  // get the restaurant using API
    val food: Menu = menu1
    val restaurantReviews: List<CustomerReview> = listReviews

    var isFavorite by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(0) }
    // State for showing/hiding the input field
    var isNoteVisible by remember { mutableStateOf(false) }

    // State for storing the note text
    var note by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomBar(quantity, food.price ) { newQuantity ->
                quantity = newQuantity
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                // .padding(end = 2.dp)

            ) {
                FoodCardImageSection(pictures= imageUrls)

                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        addFoodToFavorite(foodID = food.menuId)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd) // Position icon in the top right
                        .padding(8.dp) // Space from the edge
                ) {
                    // Use Favorite or FavoriteBorder icon based on the state
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) defaultCustomColorScheme.primary500 else defaultCustomColorScheme.ink300 // Change color based on state
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            // name section
            Text(
                text = food.name,
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

                Spacer(Modifier.width(16.dp))

                Text(
                    text = restau.address,
                    style = defaultCustomTypographyScheme.p_smallBold,
                    color = defaultCustomColorScheme.ink500
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Description section
            SectionTitle(title = "Description")
            Text(
                text = food.description ?: " No Description Found",
                style = defaultCustomTypographyScheme.p_small,
                color = defaultCustomColorScheme.ink400,
                lineHeight = 20.sp,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(title = "Customize Your Food")

            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(defaultCustomColorScheme.primary100)
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Add Note",
                    style = defaultCustomTypographyScheme.p_smallBold,
                    color = defaultCustomColorScheme.ink500
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
                    style = defaultCustomTypographyScheme.p_small,
                    color = defaultCustomColorScheme.ink400
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
                        focusedBorderColor = defaultCustomColorScheme.primary200, // Set primary color when focused
                        unfocusedBorderColor = defaultCustomColorScheme.primary100 // Set border color when not focused
                    )
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle(title = "Reviews")

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                restaurantReviews.forEach { review ->
                    CustomerReview(
                        customerReview = review
                    )
                }

            }

        }
    }
}

@Composable
fun FoodCardImageSection(pictures: List<String>) {
    // Image Section
    Row(
        modifier = Modifier.fillMaxWidth().height(400.dp)
    ) {
        // Left: Main Image
        Box(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = pictures[0], // Load image from URL
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(2.dp))

        // Right: Two Small Images
        Column(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = pictures[1], // Load image from URL
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(2.dp))
            AsyncImage(
                model = pictures[2], // Load image from URL
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

fun addFoodToFavorite( foodID: Int){
    /*handle add menue to fav logic using API*/
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

        @Composable
        fun previewFoodDetailsScreen() {


            FoodDetailsScreen(
                navController = rememberNavController(),
                restaurantID = 1,
                foodID = 1
            )
        }

