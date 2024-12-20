package com.example.food_delivery_app.components

sealed class Destination(val route:String) {
    object Home:Destination("home")
    object Detail:Destination("detail")
}