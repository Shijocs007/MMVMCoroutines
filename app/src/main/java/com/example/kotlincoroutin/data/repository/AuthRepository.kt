package com.example.kotlincoroutin.data.repository

import com.example.kotlincoroutin.data.db.AppDataBase
import com.example.kotlincoroutin.data.db.entities.User
import com.example.kotlincoroutin.data.network.MyApi
import com.example.kotlincoroutin.data.network.SafeApiRequest
import com.example.kotlincoroutin.data.network.responses.AuthResponse


class AuthRepository(
    private val api : MyApi,
    private val db : AppDataBase
) : SafeApiRequest() {

    suspend fun userLogin(email : String, password : String) : AuthResponse {

        return apiRequest { api.userLogin(email, password) }

    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}