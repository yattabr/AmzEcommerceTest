package com.yattatech.amzecommerce

import android.app.Application
import com.yattatech.amzecommerce.koin.networkModule
import com.yattatech.amzecommerce.koin.repositoryModule
import com.yattatech.amzecommerce.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AmzEcommerceApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AmzEcommerceApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}