package com.example.jsonfreemakerapp.data

import com.example.jsonfreemakerapp.config.ClientConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonFreeMakerService (
    private val config: ClientConfig
) : JsonFreeMakerInterface by Retrofit.Builder()
    .baseUrl(config.jsonFreeMakerUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().(config.okHttpClient)().build())
    .build()
    .create(JsonFreeMakerInterface::class.java)