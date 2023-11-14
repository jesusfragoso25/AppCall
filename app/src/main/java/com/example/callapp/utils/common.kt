package com.example.callapp.utils

import android.content.Context
import android.widget.Toast

class common {
    companion object {
        fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(context, message, duration).show()
        }
    }
}