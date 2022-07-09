package com.example.sopt30th.api

import com.example.sopt30th.response.ResponseRepoInfo
import com.example.sopt30th.response.ResponseUserInfo
import retrofit2.Call
import retrofit2.http.GET

interface GithubApiService{
    @GET("users/uxri/followers")
    fun getFollowerInfo(): Call<List<ResponseUserInfo>>

    @GET("users/uxri/following")
    fun getFollowingInfo(): Call<List<ResponseUserInfo>>

    @GET("users/uxri/repos")
    fun getRepoInfo(): Call<List<ResponseRepoInfo>>
}