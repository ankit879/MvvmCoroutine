package com.example.mvvmcoroutine.repository

import com.example.mvvmcoroutine.Resource
import com.example.mvvmcoroutine.api.ApiServices
import com.example.mvvmcoroutine.api.retrofit
import com.example.mvvmcoroutine.model.GetCardResponse
import retrofit2.Response

class GetCardRepository() {
    suspend fun getCard(access_token: String): Response<GetCardResponse> {
        return retrofit.getCardList(access_token)
    }

}


class YourRepository(private val apiService: ApiServices) {

    suspend fun getSomething(access_token: String): Resource<GetCardResponse> {
        return try {
            val response = apiService.getCardList(access_token)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    Resource.Success(body)
                } ?: Resource.Error("Empty response body")
            } else {
                Resource.Error("Error code: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unknown error occurred")
        }
    }
}
