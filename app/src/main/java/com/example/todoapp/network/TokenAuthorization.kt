package com.example.todoapp.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenAuthorization : Interceptor {
    private val token = "Seilkhanov_N"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}