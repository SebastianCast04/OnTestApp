package database.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val password: String
)

@Entity(tableName = "clients")
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String
)

/*@Entity(tableName = "phones")
data class Phone(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clientId: Int,
    val phoneNumber: String
)

 */
