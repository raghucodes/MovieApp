package com.example.movieapp.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Exception

class ErrorInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())
        when (response.code) {
            401 -> throw Exception()
            403 -> throw Exception()
            else -> {
            }
        }
        return response
    }
}