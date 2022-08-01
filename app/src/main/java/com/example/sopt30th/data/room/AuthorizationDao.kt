package com.example.sopt30th.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AuthorizationDao {
    @Query("SELECT * FROM AuthorizationDto WHERE userId = :id")
    fun getAuthorization(id: String): AuthorizationDto

    @Insert
    fun insertAuthorization(authorizationDto: AuthorizationDto)

    @Delete
    fun deleteAuthorization(authorizationDto: AuthorizationDto)
}