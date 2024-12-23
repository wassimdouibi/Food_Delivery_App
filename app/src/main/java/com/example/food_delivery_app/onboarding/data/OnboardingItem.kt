package com.example.food_delivery_app.onboarding.data

import com.example.food_delivery_app.R

class OnboardingItem(
    val title: Int,
    val description: Int,
    val image: Int
) {
    companion object {
        fun get() : List<OnboardingItem> {
            return listOf(
                OnboardingItem(
                    title = R.string.Onboarding_title1,
                    description = R.string.Onboarding_description1,
                    image = R.drawable.img_wavy_buddies_preparing_your_food
                ),
                OnboardingItem(
                    title = R.string.Onboarding_title2,
                    description = R.string.Onboarding_description2,
                    image = R.drawable.img_wavy_buddies_delivery_on_the_way
                ),
                OnboardingItem(
                    title = R.string.Onboarding_title3,
                    description = R.string.Onboarding_description3,
                    image = R.drawable.img_wavy_buddies_choosing_food
                )
            )
        }
    }
}
