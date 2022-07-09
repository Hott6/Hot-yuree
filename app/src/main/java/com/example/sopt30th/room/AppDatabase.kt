package com.example.sopt30th.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AuthorizationDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authorizationDao(): AuthorizationDao
}