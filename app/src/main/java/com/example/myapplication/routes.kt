package com.example.greetingapp

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Greeting(
    val userName: String
)