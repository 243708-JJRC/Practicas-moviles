package com.jjuanrc.examenu2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 3)
abstract class UserRepo : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        @Volatile private var INSTANCE: UserRepo? = null
        fun getDatabase(context: Context): UserRepo {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRepo::class.java,
                    "user"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}