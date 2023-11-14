package com.example.callapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val firstName: String?,
    val lastName: String?,
    val number: String?,
    val mail: String?

)
