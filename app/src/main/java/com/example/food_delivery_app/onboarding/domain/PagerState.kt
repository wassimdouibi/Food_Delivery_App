package com.example.food_delivery_app.onboarding.domain

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
class PagerState(
    page: Int = 0,
    offset: Float = 0f
) {
    var page = mutableStateOf(page)
    var offset = mutableStateOf(offset)

    val pageOffset: Float
        get() = page.value + offset.value
}