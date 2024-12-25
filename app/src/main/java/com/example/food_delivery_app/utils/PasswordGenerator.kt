package com.example.food_delivery_app.utils

import kotlin.random.Random

class PasswordGenerator {
    companion object {
        fun generatePassword(length: Int): String {
            val chars =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:',<.>/?`~"
            return (1..length)
                .map { Random.nextInt(0, chars.length) }
                .map(chars::get)
                .joinToString("")
        }
    }
}