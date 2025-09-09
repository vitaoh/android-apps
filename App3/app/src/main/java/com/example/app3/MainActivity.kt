package com.example.app3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isUpdating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etC = findViewById<EditText>(R.id.etC)
        val etF = findViewById<EditText>(R.id.etF)

        etC.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) return
                isUpdating = true
                if (!s.isNullOrEmpty()) {
                    val celsius = s.toString().toDoubleOrNull()
                    if (celsius != null) {
                        val fahrenheit = celsius * 9 / 5 + 32
                        etF.setText(fahrenheit.toString())
                    } else {
                        etF.setText("")
                    }
                } else {
                    etF.setText("")
                }
                isUpdating = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etF.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isUpdating) return
                isUpdating = true
                if (!s.isNullOrEmpty()) {
                    val fahrenheit = s.toString().toDoubleOrNull()
                    if (fahrenheit != null) {
                        val celsius = (fahrenheit - 32) * 5 / 9
                        etC.setText(celsius.toString())
                    } else {
                        etC.setText("")
                    }
                } else {
                    etC.setText("")
                }
                isUpdating = false
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}

