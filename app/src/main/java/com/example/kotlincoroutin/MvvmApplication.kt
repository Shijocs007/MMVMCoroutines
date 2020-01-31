package com.example.kotlincoroutin

import android.app.Application
import com.example.kotlincoroutin.data.db.AppDataBase
import com.example.kotlincoroutin.data.network.MyApi
import com.example.kotlincoroutin.data.network.NetworkConnectionIntercepter
import com.example.kotlincoroutin.data.repository.AuthRepository
import com.example.kotlincoroutin.viewmodel.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MvvmApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MvvmApplication))

        bind() from singleton { NetworkConnectionIntercepter(instance())}
        bind() from singleton { MyApi(instance())}
        bind() from singleton { AppDataBase(instance())}
        bind() from singleton { AuthRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}