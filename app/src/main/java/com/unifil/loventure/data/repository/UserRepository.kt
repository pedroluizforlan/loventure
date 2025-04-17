package com.unifil.loventure.data.repository

import com.unifil.loventure.data.local.dao.UserDao
import com.unifil.loventure.data.local.entity.User

class UserRepository(private val userDao: UserDao) {

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }

    suspend fun register(username: String, password: String): User {
        val existingUser = userDao.getUserByName(username)
        if (existingUser != null) throw Exception("Usuário já existe")
        val newUser = User(name = username, password = password, age = 18)
        userDao.insert(newUser)
        return userDao.getUserByName(username)!!
    }
}
