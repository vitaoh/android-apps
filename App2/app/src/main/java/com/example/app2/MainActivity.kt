package com.example.app2

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
        val btnResultado = findViewById<Button>(R.id.btnResultado)
        val etString = findViewById<EditText>(R.id.etString)
        val etInt = findViewById<EditText>(R.id.etInt)

        btnResultado.setOnClickListener {
            val inputString = etString.text.toString()
            val inputInt = etInt.text.toString().toIntOrNull()

            if (inputString.isEmpty()) {
                textV.text = "Caixa de texto vazia!"
            } else {
                if(inputInt != null && inputInt >= 0 && inputInt <= 9) {
                    textV.text = List(inputInt) { inputString }.joinToString(separator = "\n")
                }
                else {
                    textV.text = "Formato invalido para repetição!"
                }
            }
        }
    }
}