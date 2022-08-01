package com.example.sopt30th.service

import com.example.sopt30th.data.model.response.ResponseRepoInfo
import com.example.sopt30th.data.model.response.ResponseUserInfo
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