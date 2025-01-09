package com.example.food_delivery_app.components

import com.example.food_delivery_app.R

/*rating food tetne7a */
data class Food(
    val id : Int,
    val name : String,
    // val restaurant: Restaurant,
    val remoteViews: List<String>,
    val price: Double,
    val rating: Int,
    val info : String,
    val category: String
)

data class Restaurant(
    val id : Int,
    val name: String,
    val location: String,
    val rating: Double,
    val reviews: Int,
    val phone: String,
    val email: String,
    val instagram: String,
    val facebook: String,
    val locationImage : Int,
    val restaurantPictures: List<Int>, // List of images
    val topPicks: List<String>? , // Top picks tags
    val foodList : List<Food>,
    val reviewsList: List<CustomerReview>?,
    //val cuisine: Cuisine

)

data class CustomerReview(
    val id: Int,
    val title : String,
    val customerUsername : String,
    val text : String,
    val rating: Int
)

data class Cuisine(
    val name: String,
    val imageRes: Int
)


/************************ DATA ****************************/

val foods = listOf(
    Food(
        id = 1,
        name = "Margherita Pizza",
        remoteViews = listOf(
            "https://example.com/images/margherita1.jpg",
            "https://example.com/images/margherita2.jpg"
        ),
        price = 8.99,
        rating = 5,
        info = "A classic Italian pizza topped with fresh mozzarella, basil, and tomato sauce.",
        category = "Desserts"

    ),
    Food(
        id = 2,
        name = "Cheeseburger",
        remoteViews = listOf(
            "https://example.com/images/cheeseburger1.jpg",
            "https://example.com/images/cheeseburger2.jpg"
        ),
        price = 6.49,
        rating = 4,
        info = "A juicy beef burger topped with melted cheese, lettuce, tomato, and a toasted bun." ,
        category = "Breakfast"

    ),
    Food(
        id = 3,
        name = "Caesar Salad",
        remoteViews = listOf(
            "https://example.com/images/caesarsalad1.jpg",
            "https://example.com/images/caesarsalad2.jpg"
        ),
        price = 5.99,
        rating = 4,
        info = "Fresh romaine lettuce, Parmesan cheese, and croutons, served with Caesar dressing.",
        category = "Breakfast"

    ),
    Food(
        id = 4,
        name = "Spaghetti Bolognese",
        remoteViews = listOf(
            "https://example.com/images/spaghetti1.jpg",
            "https://example.com/images/spaghetti2.jpg"
        ),
        price = 10.99,
        rating = 5,
        info = "Traditional Italian pasta dish with a rich and savory meat sauce.",
        category = "Lunch & Dinner"

    )
)

val customerReviews = listOf(
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
    ),
    CustomerReview(
        id = 5,
        title = "Sushi Heaven",
        customerUsername = "sushilover98",
        text = "The sushi platter was beautifully presented and everything tasted fresh. Will order again!",
        rating = 5
    ),
    CustomerReview(
        id = 6,
        title = "Not Great",
        customerUsername = "unsatisfiedcustomer",
        text = "Ordered the Tiramisu, but it was too soggy and lacked the rich coffee flavor. Disappointed.",
        rating = 2
    )
)

// Restaurant 1
val restaurant1: Restaurant = Restaurant(
    id = 1,
    name = "Gourmet Spot",
    location = "City Center",
    rating = 4.8,
    reviews = 220,
    phone = "123-456-7891",
    email = "contact@gourmetspot.com",
    instagram = "@gourmetspot",
    facebook = "GourmetSpot",
    locationImage = R.drawable.img_map_location,
    restaurantPictures = listOf(
        R.drawable.img_food_one,
        R.drawable.img_food_two,
        R.drawable.img_food_three
    ),
    topPicks = listOf("Pasta", "Seafood", "Meat"),
    foodList = foods, // From earlier Food list
    reviewsList = customerReviews // From earlier CustomerReview list
)

// Restaurant 2
val restaurant2: Restaurant = Restaurant(
    id = 2,
    name = "Urban Bistro",
    location = "Downtown Avenue",
    rating = 4.5,
    reviews = 150,
    phone = "987-654-3210",
    email = "info@urbanbistro.com",
    instagram = "@urbanbistro",
    facebook = "UrbanBistro",
    locationImage = R.drawable.img_map_location,
    restaurantPictures = listOf(
        R.drawable.img_food_one,
        R.drawable.img_food_two,
        R.drawable.img_food_three
    ),
    topPicks = listOf("Burgers", "Fries", "Cocktails"),
    foodList = foods, // From earlier Food list
    reviewsList = customerReviews // From earlier CustomerReview list
)

// Restaurant 3
val restaurant3: Restaurant = Restaurant(
    id = 3,
    name = "Rustic Haven",
    location = "Countryside",
    rating = 4.7,
    reviews = 180,
    phone = "456-789-1234",
    email = "contact@rustichaven.com",
    instagram = "@rustichaven",
    facebook = "RusticHaven",
    locationImage = R.drawable.img_map_location,
    restaurantPictures = listOf(
        R.drawable.img_food_one,
        R.drawable.img_food_two,
        R.drawable.img_food_three
    ),
    topPicks = listOf("Pizza", "Salads", "Desserts"),
    foodList = foods , // From earlier Food list
    reviewsList = customerReviews // From earlier CustomerReview list
)

val cuisinesList = listOf(
    Cuisine("Algerian", R.drawable.img_food_one),
    Cuisine("Italian", R.drawable.img_food_two),
    Cuisine("Japanese", R.drawable.img_food_three),
    Cuisine("French", R.drawable.img_food_one)
)

val categoriesList = listOf(
    Cuisine("Vegan", R.drawable.img_food_three),
    Cuisine("Dessert", R.drawable.img_food_two),
    Cuisine("Seafood", R.drawable.img_food_one),
    Cuisine("Snacks", R.drawable.img_food_three)
)