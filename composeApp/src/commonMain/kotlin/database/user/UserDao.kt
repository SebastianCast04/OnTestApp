package database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
}

@Dao
interface ClientDao {
    @Query("SELECT * FROM clients")
    suspend fun getAllClients(): List<Client>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: Client)

    @Delete
    suspend fun deleteClient(client: Client)
}

/*@Dao
interface PhoneDao {
    @Insert
    suspend fun insertPhone(phone: Phone)

    @Delete
    suspend fun deletePhone(phone: Phone)

    @Query("SELECT * FROM phones WHERE clientId = :clientId")
    suspend fun getPhonesByClientId(clientId: Int): List<Phone>
}

 */