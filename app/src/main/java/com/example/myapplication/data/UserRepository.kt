package com.example.myapplication.data

class UserRepository(
    private val userDao: UserDao,
    private val userApi : UserApi
) {
    suspend fun getUser() : Result<List<User>> {
        return try {
            val usersGet = userApi.getUsers()
            userDao.upSertAll(usersGet)
            return Result.success(usersGet)
        } catch (e : Exception) {
            val userDao = userDao.getAll()
            if (userDao.isNotEmpty()) Result.success(userDao)
            else Result.failure(e)
        }
    }
}