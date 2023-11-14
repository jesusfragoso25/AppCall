package com.example.callapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.callapp.data.model.contact

@Dao
interface contactDao{
    @Query("SELECT * FROM contact ORDER BY firstName ASC")
    fun getAllContacts(): LiveData<List<contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: contact)

    @Delete
    suspend fun deleteContact(contact: contact)

    @Update
    suspend fun updateContact(contact: contact)

    @Query("SELECT * from contact WHERE firstName OR lastName LIKE '%' || :query || '%'")
    fun findContactByName(query: String): LiveData<List<contact>>

    @Query("SELECT * FROM contact WHERE id = :contactId")
    fun getContactById(contactId: Int): LiveData<contact?>
}