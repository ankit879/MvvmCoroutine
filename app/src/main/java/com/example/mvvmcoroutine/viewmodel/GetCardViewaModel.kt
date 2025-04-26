package com.example.mvvmcoroutine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcoroutine.Resource
import com.example.mvvmcoroutine.model.GetCardResponse
import com.example.mvvmcoroutine.repository.GetCardRepository
import com.example.mvvmcoroutine.repository.YourRepository
import kotlinx.coroutines.launch

class GetCardViewModel : ViewModel() {
    private var repository = GetCardRepository()
    private val _cardList = MutableLiveData<Resource<GetCardResponse>>()
    val cardList: LiveData<Resource<GetCardResponse>> = _cardList
    fun getCard(access_token: String) {
        viewModelScope.launch {
            _cardList.value = Resource.Loading
            try {
                val response = repository.getCard(access_token)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { body ->
                        _cardList.value = Resource.Success(body)
                    } ?: run {
                        _cardList.value = Resource.Error("Empty response body")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown API error"
                    _cardList.value = Resource.Error(errorMessage)
                }
            } catch (
                e: Exception
            ) {
                _cardList.value = Resource.Error(e.localizedMessage ?: "Network error")


            }

        }


    }


}

class YourViewModel(private val repository: YourRepository) : ViewModel() {

    private val _yourData = MutableLiveData<Resource<GetCardResponse>>()
    val yourData: LiveData<Resource<GetCardResponse>> = _yourData

    fun fetchData(access_token: String) {
        viewModelScope.launch {
            _yourData.value = Resource.Loading
            _yourData.value = repository.getSomething(access_token)
        }
    }
}

