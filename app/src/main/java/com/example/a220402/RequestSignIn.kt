package com.example.a220402

data class RequestSignIn (
    val email: String, //id가 아니라 email로 했으니까 Serialized 없어두댕
    val password: String
)