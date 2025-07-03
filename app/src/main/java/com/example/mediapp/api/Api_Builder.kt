package com.example.mediapp.api

import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api_Builder {

    val api: Api_Service = Retrofit.Builder()
        .client(OkHttpClient.Builder().build())
        .baseUrl("https://harsh01815.pythonanywhere.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api_Service::class.java)
}
