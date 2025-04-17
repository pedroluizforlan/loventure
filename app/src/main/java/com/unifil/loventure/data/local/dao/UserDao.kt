package com.unifil.loventure.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.unifil.loventure.data.local.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE id = :userId")
    suspend fun getUser(userId: Int): User

    @Query("SELECT * FROM user_table WHERE name = :username")
    suspend fun getUserByName(username: String): User?

    @Query("SELECT * FROM user_table WHERE name = :username AND password = :password")
    suspend fun login(username: String, password: String): User?
}
