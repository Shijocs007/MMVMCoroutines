package com.example.kotlincoroutin.data.network.responses

import com.example.kotlincoroutin.data.db.entities.User

data class AuthResponse (
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)