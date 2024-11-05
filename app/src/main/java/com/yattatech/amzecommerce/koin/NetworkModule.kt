package com.yattatech.amzecommerce.koin

import com.yattatech.amzecommerce.data.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }


    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()


    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_GET)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    fun provideService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    companion object {
        const val BASE_URL_GET = "https://real-time-amazon-data.p.rapidapi.com/"
    }
}

val networkModule = module {
    single { NetworkModule().provideHttpClient() }
    single { NetworkModule().provideConverterFactory() }
    single { NetworkModule().provideRetrofit(get(), get()) }
    single { NetworkModule().provideService(get()) }
}