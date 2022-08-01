package com.example.sopt30th.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sopt30th.data.model.request.RequestSignIn
import com.example.sopt30th.data.model.response.ResponseSignIn
import com.example.sopt30th.data.model.response.ResponseWrapper
import com.example.sopt30th.util.ServiceCreator
import com.example.sopt30th.util.SingleLiveEvent
import com.example.sopt30th.util.enqueueUtil
import retrofit2.Call
import timber.log.Timber

class SignInViewModel() : ViewModel() {
    var userId = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()


    private var _isSuccess = SingleLiveEvent<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _isEmpty = SingleLiveEvent<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    fun login(){
        if (!userId.value.isNullOrBlank() && !userPassword.value.isNullOrBlank()) {
            loginNetwork()
        } else {
        }
    }

    fun loginNetwork() {
        val requestSignIn = RequestSignIn(
                email = userId.toString(),
                password = userPassword.toString()
        )
        val call: Call<ResponseWrapper<ResponseSignIn>> =
                ServiceCreator.soptService.postLogin(requestSignIn)
        //싱글톤 객체를 이용해 Retrofit이 만들어준 interface 구현체에 접근하여 Call 객체를 받아온다
        call.enqueueUtil(
                onSuccess = {
                            _isSuccess.value = true
                },
                onError = {
                    Timber.e("Error")
                }
        )
    }
}