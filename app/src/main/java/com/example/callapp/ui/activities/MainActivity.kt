package com.example.callapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.callapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAction = findViewById<Button>(R.id.addcontact)

        btnAction.setOnClickListener{
            val i = Intent(this, addContact::class.java)
            startActivity(i)
        }
    }
}