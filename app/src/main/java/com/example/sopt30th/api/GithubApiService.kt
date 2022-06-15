package com.example.sopt30th.api

import com.example.sopt30th.response.ResponseUserInfo
import retrofit2.Call
import retrofit2.http.GET

interface GithubApiService{
    @GET("users/uxri")
    fun getUserInfo(): Call<ResponseUserInfo>

    @GET("users/uxri/followers")
    fun getFollowingInfo(): Call<List<ResponseUserInfo>>
}