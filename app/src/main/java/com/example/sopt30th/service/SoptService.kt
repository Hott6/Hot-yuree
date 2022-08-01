package com.example.sopt30th.service

import com.example.sopt30th.data.model.request.RequestSignIn
import com.example.sopt30th.data.model.request.RequestSignUp
import com.example.sopt30th.data.model.response.ResponseSignIn
import com.example.sopt30th.data.model.response.ResponseSignUp
import com.example.sopt30th.data.model.response.ResponseWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseWrapper<ResponseSignIn>> //동기적, 비동기적으로 Type을 받아오는 객체

    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUp
    ): Call<ResponseWrapper<ResponseSignUp>>
}