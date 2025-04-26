package com.example.mvvmcoroutine.practical

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcoroutine.ErrorModel
import com.example.mvvmcoroutine.R
import com.example.mvvmcoroutine.Result
import com.example.mvvmcoroutine.model.GetCardResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface APIService {
    @GET("users/saveCardList")
    suspend fun getCardList(
        @Header("access_token") access_token: String,
    ): Response<GetCardResponse>
}

val retrofit: APIService by lazy {
    Retrofit.Builder().baseUrl("https://admin.intlaqride.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(APIService::class.java)
}

class getCardRepo() {
    suspend fun getCardList(access_token: String): Response<GetCardResponse> {
        return retrofit.getCardList(access_token)
    }
}

class getCardViewModel : ViewModel() {
    val repo = getCardRepo()
    private val _cardResponse = MutableLiveData<Result<GetCardResponse>>()
    val cardResponse: LiveData<Result<GetCardResponse>> = _cardResponse

    fun getCard(access_token: String) {
        viewModelScope.launch {
            _cardResponse.value = Result.Loading
            try {
                var response = repo.getCardList(access_token)
                Log.d("response123", "getCard: response  ${response.errorBody()}")
                if (response.isSuccessful && response.body() != null) {
                    _cardResponse.value = Result.Success(response.body()!!)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown API error"
                    val jsonObject = JSONObject(errorMessage)
                    val msg = jsonObject.optString("message", "Unknown API error")
                    _cardResponse.value = Result.Error(msg)
                }
            } catch (e: Exception) {
                _cardResponse.value = Result.Error(e.localizedMessage ?: "Network error")
            }
        }
    }
}

