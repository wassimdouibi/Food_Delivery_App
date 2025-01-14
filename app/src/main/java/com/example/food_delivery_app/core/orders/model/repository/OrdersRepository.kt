package com.example.food_delivery_app.core.orders.model.repository

import com.example.food_delivery_app.core.orders.model.entity.Cart
import com.example.food_delivery_app.core.orders.model.entity.Order
import com.example.food_delivery_app.core.orders.model.service.OrdersService

class OrdersRepository(private val ordersService: OrdersService) {



    suspend fun addMenuToCart(userId: Int, menuId: Int): Int {
        return try {
            ordersService.addMenuToCart(userId, menuId)
        } catch (e: Exception) {
            throw Exception("Error addMenuToCart: ${e.message}")
        }
    }

    suspend fun getCartByUserId(userId: Int): Cart {
        return try {
            ordersService.getCartByUserId(userId)
        } catch (e: Exception) {
            throw Exception("Error getCartByUserId: ${e.message}")
        }
    }

    suspend fun deleteCartByUserId(userId: Int): Boolean {
        return try {
            ordersService.deleteCart(userId)
        } catch (e: Exception) {
            throw Exception("Error deleteCartByUserId: ${e.message}")
        }
    }


    suspend fun validateCart(userId: Int, deliveryAddress: String, deliveryNote: String): Order {
        return try {
            ordersService.validateCart(userId, deliveryAddress, deliveryNote)
        } catch (e: Exception) {
            throw Exception("Error validateCart: ${e.message}")
        }
    }



    suspend fun getOrdersByUserId(userId: Int): List<Order> {
        return try {
            ordersService.getOrdersByUserId(userId)
        } catch (e: Exception) {
            throw Exception("Error getOrdersByUserId: ${e.message}")
        }
    }
    suspend fun deleteOrderById(orderId: Int): Boolean {
        return try {
            ordersService.deleteOrder(orderId)
        } catch (e: Exception) {
            throw Exception("Error getOrdersByUserId: ${e.message}")
        }
    }
    suspend fun createOrder(orderRequest: Order): Int {
        return try {
            ordersService.createOrder(orderRequest)
        } catch (e: Exception) {
            throw Exception("Error getOrdersByUserId: ${e.message}")
        }
    }





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