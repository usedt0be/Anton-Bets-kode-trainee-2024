package com.example.users.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UsersEntity::class], version = 1, exportSchema = false)
abstract class UsersDatabase: RoomDatabase() {
    abstract val usersDao: UsersDao


    companion object {
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(context: Context): UsersDatabase {
            INSTANCE = Room.databaseBuilder(
                context, UsersDatabase:: class.java, "Users")
                .fallbackToDestructiveMigration()
                .build()

            return INSTANCE as UsersDatabase
        }
    }
}