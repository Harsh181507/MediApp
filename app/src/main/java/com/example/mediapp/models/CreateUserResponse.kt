package com.example.mediapp.models

data class CreateUserResponse(
    val message: String,
    val status: Int,
    val isApproved: Int
)