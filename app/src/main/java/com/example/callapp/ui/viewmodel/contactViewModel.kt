package com.example.callapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.callapp.data.model.contact
import com.example.callapp.data.repository.repository

class contactViewModel(application: Application) : AndroidViewModel(application) {
    private val contactRepository = repository(application)

    private val _contactSaved = MutableLiveData<Boolean>()
    val contactSaved: LiveData<Boolean>
        get() = _contactSaved

    private val _contactEdit = MutableLiveData<Boolean>()
    val contactEdit: LiveData<Boolean>
        get() = _contactEdit

    private val _contactDelete = MutableLiveData<Boolean>()
    val contactDelete: LiveData<Boolean>
        get() = _contactDelete

    private val _contactList = MutableLiveData<List<contact>>()
    val contactList: LiveData<List<contact>>
        get() = _contactList

    fun createContact(
        name: String,
        lastName: String,
        phoneNumber: String,
        email: String
    ) {
        viewModelScope.launch {

            repository.createContact(name, lastName, phoneNumber, email)
            _contactSaved.value = true

        }
    }

    fun getContacts(){
        viewModelScope.launch {
            val contacts = repository.getContacts()
            _contactList.value = contacts
        }
    }

    fun editContact(contactId: Long,
                    firstName: String,
                    lastName: String,
                    phoneNumber: String,
                    email: String) {

        viewModelScope.launch {
            repository.editContact(contactId, firstName, lastName, phoneNumber, email)
            _contactEdit.value = true
        }
    }

    fun deleteContact(contactId: Long) {
        viewModelScope.launch {
            repository.deleteContact(contactId)
            _contactDelete.value = true
        }
    }
}