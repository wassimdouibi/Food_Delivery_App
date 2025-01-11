package com.example.food_delivery_app.core.orders.model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.core.orders.model.entity.Order
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface OrdersService {
    @GET("orders/filter")
    suspend fun getOrdersWithSpecificStatus(status: String): List<Order>

}