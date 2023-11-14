package com.example.callapp.data.repository

import androidx.lifecycle.LiveData
import com.example.callapp.data.db.contactDao
import com.example.callapp.data.model.contact

class repository constructor(private val contactDao: contactDao) {

    fun getAllContacts() : LiveData<List<contact>?> = contactDao.getAllContacts()

    suspend fun insertContact(contact: contact) = contactDao.insertContact(contact)

    suspend fun deleteContact(contact: contact) = contactDao.deleteContact(contact)

    suspend fun updateContact(contact: contact) = contactDao.updateContact(contact)

    fun findContactByName(query: String): LiveData<List<contact>> = contactDao.findContactByName(query)

    fun getContactById(id: Int): LiveData<contact?> = contactDao.getContactById(id)
}