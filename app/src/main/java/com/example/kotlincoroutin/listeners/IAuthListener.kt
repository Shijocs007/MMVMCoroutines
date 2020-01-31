package com.example.kotlincoroutin.listeners

import androidx.lifecycle.LiveData
import com.example.kotlincoroutin.data.db.entities.User

interface IAuthListener {
    fun onstarted()
    fun onSuccess(user: User)
    fun onFailed(message : String)
}