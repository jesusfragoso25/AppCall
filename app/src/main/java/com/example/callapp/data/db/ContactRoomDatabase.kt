package com.aishwaryakamal.contactspro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.callapp.data.db.contactDao
import com.example.callapp.data.model.contact

@Database(entities=[contact::class], version = 1)
abstract class ContactRoomDatabase : RoomDatabase(){
    abstract fun contactDao(): contactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactRoomDatabase? = null;

        fun getInstance(context: Context): ContactRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ContactRoomDatabase::class.java,
                    name = "callApp_DB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}