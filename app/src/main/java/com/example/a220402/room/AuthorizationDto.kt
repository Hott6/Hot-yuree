package com.example.a220402.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthorizationDto(
    @PrimaryKey val userId: String,
    @ColumnInfo(name = "autoLogin") val autoLogin: Boolean
)
