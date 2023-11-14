package com.example.callapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.callapp.data.repository.repository

class contactViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository = repository(application)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    private val _userSaved = MutableLiveData<Boolean>()
    val userSaved: LiveData<Boolean>
        get() = _userSaved

    private val _userList = MutableLiveData<List<contactViewModel>>()
    val userList: LiveData<List<contactViewModel>>
        get() = _userList

    fun saveContact(
        val firstName: String?,
        val lastName: String?,
        val number: String?,
        val mail: String?
    ) {
        viewModelScope.launch {
            userRepository.saveContact(firstName, lastName, number, mail)
            _userSaved.value = true
        }
    }

    fun getUsers(){
        viewModelScope.launch {
            val contacts = userRepository.getAllContacts()
            _userList.value = contacts
        }
    }
}