package com.example.jsonfreemakerapp.config

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ClientConfig {

    var jsonFreeMakerUrl : HttpUrl = HttpUrl.parse("https://jsonplaceholder.typicode.com/")!!
    val okHttpClient: OkHttpClient.Builder.() -> OkHttpClient.Builder = {
        retryOnConnectionFailure(false)
        connectTimeout(15, TimeUnit.SECONDS)
        readTimeout(15, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
    }

}