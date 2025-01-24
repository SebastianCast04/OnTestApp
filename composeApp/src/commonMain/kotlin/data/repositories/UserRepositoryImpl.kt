package data.repositories

import database.user.UserDao

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> {
        val validEmail = "onoff@gmail.com"
        val validPassword = "test1234"

        return if (email == validEmail && password == validPassword) {
            Result.success(true)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }
}