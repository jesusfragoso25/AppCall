package com.aishwaryakamal.contactspro.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.callapp.data.db.contactDao
import com.example.callapp.data.model.contact

@Database(entities=[contact::class], version = 1)
abstract class ContactRoomDatabase : RoomDatabase(){
    abstract fun contactDao(): contactDao
}