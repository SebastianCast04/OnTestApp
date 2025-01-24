package com.plcoding.room_cmp.database

import android.content.Context
import androidx.room.Room
import database.user.UserDatabase

fun getUserDataBase(context: Context): UserDatabase {
    val dbFile = context.getDatabasePath("user.db")
    return Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        dbFile.absolutePath
    ).build()
}