package com.example.food_delivery_app.core.orders.model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.core.home.model.services.HomeService
import com.example.food_delivery_app.core.orders.model.entity.Cart
import com.example.food_delivery_app.core.orders.model.entity.Order
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface OrdersService {
    @GET("orders/user/{id}")
    suspend fun getOrdersByUserId(@Path("id") userId: Int): List<Order>
    @DELETE("orders/{id}")
    suspend fun deleteOrder(@Path("id") orderId: Int): Boolean
    @POST("orders/add")
    suspend fun createOrder(@Body orderRequest: Order): Int






    @POST("carts/add/user/{id}")
    suspend fun createCart(@Path("id") userId: Int) : Cart

    @GET("orders/filter")
    suspend fun getOrdersWithSpecificStatus(status: String): List<Order>

    @POST("menus/add-menu-to-cart/{userId}/{menuId}")
    suspend fun addMenuToCart(@Path("userId") userId: Int, @Path("menuId") menuId: Int) : Int



    @GET("carts/{id}")
    suspend fun getCartByUserId(@Path("id") userId: Int) : Cart

    @DELETE("carts/{id}")
    suspend fun deleteCart(@Path("id") cartId: Int): Boolean

    @POST("carts/user/{id}/validate")
    suspend fun validateCart(
        @Path("id") userId: Int,
        @Path("deliveryAddress") deliveryAddress: String,
        @Path("deliveryAddress") deliveryNote: String
    ): Order

    @GET("cartItems/user/{id}")
    suspend fun getCartItemsByUserId()


    companion object {
        private var ordersInterface: OrdersService? = null
        fun getInstance(): OrdersService {
            if (ordersInterface == null) {
                ordersInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(OrdersService::class.java)
            }
            return ordersInterface!!
        }
    }
}