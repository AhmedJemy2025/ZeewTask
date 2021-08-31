package com.app.core.networking.responses

data class Result(
    val customerId: Int,
    val message: String,
    val success: Int,
    val token: String
)