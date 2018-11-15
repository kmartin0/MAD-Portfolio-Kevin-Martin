package com.kevin.a06recipeapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Food2ForkApi {
    companion object {
        const val polyApiKey = "6e725377bedca4600ab85912459e00cf"
        fun create(): Food2ForkApiService {

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("https://www.food2fork.com/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(Food2ForkApiService::class.java)
        }
    }
}