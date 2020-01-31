package com.example.kotlincoroutin.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.kotlincoroutin.data.repository.AuthRepository
import com.example.kotlincoroutin.listeners.IAuthListener
import com.example.kotlincoroutin.utils.ApiException
import com.example.kotlincoroutin.utils.Coroutines
import com.example.kotlincoroutin.utils.NoInternetException

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var email : String? = null
    var password : String? = null
    var authListener : IAuthListener? = null

    fun onLoginButtonclicked(view : View) {

        authListener?.onstarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {

            authListener?.onFailed("failed")
            return
        }

        Coroutines.main {
            try {
                val authResponse = authRepository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(authResponse.user)
                    authRepository.saveUser(authResponse.user)
                    return@main
                }
                authListener?.onFailed(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailed(e.message!!)
            } catch (e : NoInternetException) {
                authListener?.onFailed(e.message!!)
            }
        }
    }

    fun getLoggedInUser() = authRepository.getUser()
}