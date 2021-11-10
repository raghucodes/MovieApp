package com.example.movieapp.data.retrofit.interceptors

import com.example.movieapp.data.retrofit.API_KEY
import okhttp3.Interceptor

class AddQueryParms {
    companion object{
        fun addQueryParms() : Interceptor{
            return Interceptor{ chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key",API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
        }
    }
}