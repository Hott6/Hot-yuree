package com.example.a220402.api

import com.example.a220402.request.RequestSignIn
import com.example.a220402.request.RequestSignUp
import com.example.a220402.response.ResponseSignIn
import com.example.a220402.response.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn> //동기적, 비동기적으로 Type을 받아오는 객체

    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>
}