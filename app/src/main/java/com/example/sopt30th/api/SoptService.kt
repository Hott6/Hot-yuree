package com.example.sopt30th.api

import com.example.sopt30th.request.RequestSignIn
import com.example.sopt30th.request.RequestSignUp
import com.example.sopt30th.response.ResponseSignIn
import com.example.sopt30th.response.ResponseSignUp
import com.example.sopt30th.response.ResponseWrapper
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