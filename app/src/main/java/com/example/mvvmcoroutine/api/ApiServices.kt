package com.example.mvvmcoroutine.api

import com.example.mvvmcoroutine.model.GetCardResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServices {
    @GET("users/saveCardList")
    suspend fun getCardList(
        @Header("access_token") access_token: String,
    ): Response<GetCardResponse>

}

val retrofit: ApiServices by lazy {
    Retrofit.Builder().baseUrl("https://admin.intlaqride.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServices::class.java)
}