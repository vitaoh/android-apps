package com.example.app1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textV = findViewById<TextView>(R.id.textV)
        val btnMinuscula = findViewById<Button>(R.id.btnMinuscula)
        val btnMaiscula = findViewById<Button>(R.id.btnMaiuscula)
        val etNome = findViewById<EditText>(R.id.etNome)
        btnMinuscula.setOnClickListener {
            if(!etNome.text.isEmpty()) {
                textV.text = etNome.text.toString().lowercase()
            } else {
                textV.text = "Caixa de texto vazia!"
            }
        }
        btnMaiscula.setOnClickListener {
            if(!etNome.text.isEmpty()) {
                textV.text = etNome.text.toString().uppercase()
            } else {
                textV.text = "Caixa de texto vazia!"
            }
        }
    }
}