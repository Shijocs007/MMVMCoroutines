package com.example.kotlincoroutin.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlincoroutin.R
import com.example.kotlincoroutin.data.db.AppDataBase
import com.example.kotlincoroutin.data.db.entities.User
import com.example.kotlincoroutin.data.network.MyApi
import com.example.kotlincoroutin.data.network.NetworkConnectionIntercepter
import com.example.kotlincoroutin.data.repository.AuthRepository
import com.example.kotlincoroutin.listeners.IAuthListener
import com.example.kotlincoroutin.databinding.ActivitySignInBinding
import com.example.kotlincoroutin.ui.home.HomeActivity
import com.example.kotlincoroutin.utils.hideProgress
import com.example.kotlincoroutin.utils.showProgress
import com.example.kotlincoroutin.utils.toast
import com.example.kotlincoroutin.viewmodel.AuthViewModel
import com.example.kotlincoroutin.viewmodel.AuthViewModelFactory
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignInActivity : AppCompatActivity(), IAuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding : ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        val viewmodel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewmodel

        viewmodel.authListener = this

        viewmodel.getLoggedInUser().observe(this, Observer {
            if(it != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onstarted() {
        toast("login started")
        progressBar.showProgress()
    }

    override fun onSuccess(user: User) {
       toast("success")
        progressBar.hideProgress()
    }

    override fun onFailed(message : String) {
        progressBar.hideProgress()
        toast(message)
    }
}
