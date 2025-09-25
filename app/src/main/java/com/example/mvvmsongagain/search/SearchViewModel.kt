package com.example.mvvmsongagain.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsongagain.apiStuff.Retro
import com.example.mvvmsongagain.models.Data
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _searchResults = MutableLiveData<List<Data>>()
    val searchResults: LiveData<List<Data>> get() = _searchResults

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun searchSong(query: String) {
        viewModelScope.launch {
            try {
                val response = Retro.api.getSearchedSong(query)
                _searchResults.value = response.data

            } catch (e: Exception) {
                _error.value = "couldn't access the internet"
            }
        }
    }

}