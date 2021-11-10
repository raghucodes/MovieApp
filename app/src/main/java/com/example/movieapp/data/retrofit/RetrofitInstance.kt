package com.example.movieapp.data.retrofit

import com.example.movieapp.data.api.interfaces.BASE_URL
import com.example.movieapp.data.api.interfaces.MovieApi
import com.example.movieapp.interceptors.ErrorInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY = "28f6ac686718252e577b2415214b84e5"
class RetrofitInstance {
    companion object {
        private val interceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        fun httpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
                    .addNetworkInterceptor(StethoInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(25, TimeUnit.SECONDS)
            }.build()
        }

        fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        fun provideMovieApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)
    }
}