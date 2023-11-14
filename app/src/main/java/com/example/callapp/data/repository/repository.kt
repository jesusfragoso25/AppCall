package com.example.callapp.data.repository

import android.content.Context
import com.aishwaryakamal.contactspro.data.local.ContactRoomDatabase
import com.example.callapp.data.model.contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class repository (context: Context) {

    private val database = ContactRoomDatabase.getInstance(context)

    suspend fun getContacts(): List<contact>{
        return withContext(Dispatchers.IO) {
            database.contactDao().getAllContacts()
        }
    }

    suspend fun createContact(
        name: String,
        lastName: String,
        phoneNumber: String,
        email: String
    ) {
        withContext(Dispatchers.IO) {
            val newContact = contact(
                firstName = name,
                lastName = lastName,
                number = phoneNumber,
                mail = email
            )
            database.contactDao().insertContact(newContact)
        }
    }


    suspend fun editContact(
        idContact: Long,
        name: String,
        lastName: String,
        phoneNumber: String,
        email: String
    ) {

        withContext(Dispatchers.IO) {
            val contactFound = database.contactDao().getContactById(idContact).idContact
            val editContact = contact(
                idContact = contactFound,
                firstName = name,
                lastName = lastName,
                number = phoneNumber,
                mail = email
            )
            database.contactDao().updateContact(editContact)
        }
    }

    suspend fun deleteContact(idContact: Long) {
        withContext(Dispatchers.IO) {
            val contactFound = database.contactDao().getContactById(idContact)
            database.contactDao().deleteContact(contactFound)
        }
    }

}