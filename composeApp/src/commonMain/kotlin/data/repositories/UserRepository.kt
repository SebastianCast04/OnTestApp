package data.repositories

interface UserRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
}