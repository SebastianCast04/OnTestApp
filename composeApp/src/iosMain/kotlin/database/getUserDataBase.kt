package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.user.UserDatabase
import platform.Foundation.NSHomeDirectory

fun getUserDataBase() : UserDatabase {
    val dbFile = NSHomeDirectory() + "/people.db"
    return Room.databaseBuilder<UserDatabase>(
        name = dbFile,
        factory = { UserDatabase::class.instantiateImpl()}
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}