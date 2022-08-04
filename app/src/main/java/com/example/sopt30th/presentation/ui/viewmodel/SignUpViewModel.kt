package com.example.sopt30th.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sopt30th.data.model.request.RequestSignUp
import com.example.sopt30th.util.ServiceCreator
import com.example.sopt30th.util.SingleLiveEvent
import com.example.sopt30th.util.enqueueUtil
import timber.log.Timber

class SignUpViewModel() : ViewModel() {
    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var userPw = MutableLiveData<String>()

    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    fun signUp(){
        if (!userName.value.isNullOrBlank() && !userEmail.value.isNullOrBlank() && !userPw.value.isNullOrBlank()) {
            signUpNetwork()
        } else {
        }
    }

    fun signUpNetwork() {
        val requestSignUp = RequestSignUp(
                name = userName.toString(),
                email = userEmail.toString(),
                password = userPw.toString()
        )

        val call = ServiceCreator.soptService.postSignup(requestSignUp)

        call.enqueueUtil(
                onSuccess = {
                    it.data
                    _isSuccess.value = true
                },
                onError = {
                    Timber.e("Error")
                }
        )
    }
}