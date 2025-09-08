package com.example.app3

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etC = findViewById<EditText>(R.id.etC)
        val etF = findViewById<EditText>(R.id.etF)


    }
}