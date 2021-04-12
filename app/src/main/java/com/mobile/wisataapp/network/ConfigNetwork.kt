package com.mobile.wisataapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {

    companion object {
        fun getClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            return  client
        }
        fun getRetrofit() : WisataService {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://udacoding.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()


            val service = retrofit.create(WisataService::class.java)

            return service
        }
    }
}