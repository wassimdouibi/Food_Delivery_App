package com.example.food_delivery_app.core.profile.model.service

import com.example.food_delivery_app.NetworkModule
import com.example.food_delivery_app.auth.model.service.request.*
import com.example.food_delivery_app.auth.model.service.response.UserFieldResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ProfileService {
    @POST("users/get-user-fields")
    suspend fun getUserFields(@Body request: UserFieldsRequest): Response<UserFieldResponse>

    @PUT("users/update-address")
    suspend fun updateAddress(@Body request: UpdateAddressRequest): Response<Unit>

    @PUT("users/update-name")
    suspend fun updateName(@Body request: UpdateNameRequest): Response<Unit>

    @PUT("users/update-profile-picture")
    suspend fun updateProfilePicture(@Body request: UpdateProfilePictureRequest): Response<Unit>

    @PUT("users/update-phone-number")
    suspend fun updatePhoneNumber(@Body request: UpdatePhoneNumberRequest): Response<Unit>

    companion object {
        private var profileInterface: ProfileService? = null
        fun getInstance(): ProfileService {
            if (profileInterface == null) {
                profileInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(ProfileService::class.java)
            }
            return profileInterface!!
        }
    }
}