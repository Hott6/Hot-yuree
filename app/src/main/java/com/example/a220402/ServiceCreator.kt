package com.example.a220402

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//서비스를 생성해주는 구현체 부분
object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/" //메인 서버 도메인

    private val retrofit:Retrofit = Retrofit.Builder() //생성자 호출
        .baseUrl(BASE_URL) //서버에 메인 URL 전달
        .addConverterFactory(GsonConverterFactory.create()) //gson 컨버터 연동
        .build() //Retrofit 객체 변환


    val soptService: SoptService = retrofit.create(SoptService::class.java)
    //interface 객체를 create에 넘겨 실제 구현체 생성
}