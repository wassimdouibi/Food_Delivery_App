package com.example.food_delivery_app.core.restaurants.model.entity

// val restaurantPictures: List<Int>, // List of images
// val topPicks: List<String>?, // Top picks tags

data class Restaurant(
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
    val instaLink: String?,

//    val foodList: List<Food>,
//    val topPicks: List<Tag>,
//    val restaurantPics: List<RestaurantPic>,
)
