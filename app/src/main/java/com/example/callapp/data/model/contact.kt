package com.example.callapp.data.model

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("contact")
data class contact(
    @PrimaryKey(autoGenerate = true) val idContact: Long? = null,
    @ColumnInfo("firstName") val firstName: String?,
    @ColumnInfo("lastName") val lastName: String?,
    @ColumnInfo("number") val number: String?,
    @ColumnInfo("mail") val mail: String?

)
