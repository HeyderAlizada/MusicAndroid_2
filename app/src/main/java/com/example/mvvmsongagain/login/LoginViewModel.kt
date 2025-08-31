package com.example.mvvmsongagain.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel() : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> get() = _isSuccessful


    private val _savedRem = MutableLiveData<Boolean>()
    val savedRem: LiveData<Boolean> get() = _savedRem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun setRememberMe(value: Boolean) {
        _savedRem.value = value
        Pref.setRememberMe(_savedRem.value)
    }

    fun loadRememberMe() {
        _savedRem.value = Pref.getRememberMe()
    }

    fun LoginFirebase(email: String, password: String) {
        _isLoading.value = true

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { response ->

            _isLoading.value = false;

            if (response.isSuccessful) {
                _isSuccessful.value = true


            } else {
                _isSuccessful.value = false
            }

        }
    }


    fun SignupFirebase(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { response ->
            if (response.isSuccessful) {
                _isSuccessful.value = true
            } else {
                _isSuccessful.value = false
            }

        }
    }

}