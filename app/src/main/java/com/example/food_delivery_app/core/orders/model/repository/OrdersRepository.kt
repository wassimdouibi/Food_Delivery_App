package com.example.food_delivery_app.core.orders.model.repository

import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.model.service.OrdersService

class OrdersRepository(private val ordersService: OrdersService) {



    suspend fun getOrdersWithSpecificStatus(status: String): List<Order> {
        return try {
            val response = ordersService.getOrdersWithSpecificStatus(status)
            response.ifEmpty {
                emptyList()
            }
        } catch (e: Exception) {
            throw Exception("Error fetching categories: ${e.message}")
        }
    }
}