package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAll() : List<User>

    @Upsert()
    suspend fun upSertAll(user: List<User>)

}