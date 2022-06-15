package com.example.sopt30th.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthorizationDto(
    @PrimaryKey val userId: String,
    @ColumnInfo(name = "autoLogin") val autoLogin: Boolean
)
