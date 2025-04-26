package com.example.mvvmcoroutine.repository

import com.example.mvvmcoroutine.Result
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

    suspend fun getSomething(access_token: String): Result<GetCardResponse> {
        return try {
            val response = apiService.getCardList(access_token)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    Result.Success(body)
                } ?: Result.Error("Empty response body")
            } else {
                Result.Error("Error code: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An unknown error occurred")
        }
    }
}
