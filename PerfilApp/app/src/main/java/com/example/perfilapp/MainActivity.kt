package com.example.perfilapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnChange = findViewById<Button>(R.id.btnChange)
        val editName = findViewById<EditText>(R.id.typeName)
        val editEmail = findViewById<EditText>(R.id.typeEmail)
        val tvClientName = findViewById<TextView>(R.id.tvClientName)
        val tvClientEmail = findViewById<TextView>(R.id.tvClientEmail)
        val btnDayNight = findViewById<Button>(R.id.btnTheme)
        val btnLang = findViewById<Button>(R.id.btnLang)

        btnChange.setOnClickListener {
            val newName = editName.text.toString().trim()
            val newEmail = editEmail.text.toString().trim()

            // Atualiza os TextViews do cliente somente se valores não estiverem vazios
            if (newName.isNotEmpty()) {
                tvClientName.text = newName
            }

            if (newEmail.isNotEmpty()) {
                tvClientEmail.text = newEmail
            }
        }

        btnDayNight.setOnClickListener {
            val isNight = AppCompatDelegate.getDefaultNightMode() ==
                    AppCompatDelegate.MODE_NIGHT_YES
            val mode = if (isNight) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }


        btnLang.setOnClickListener {
            val idiomaAtual = AppCompatDelegate.getApplicationLocales()
            val usandoIdiomaPadrao =
                !idiomaAtual.isEmpty && idiomaAtual[0]?.language == "en"
            val proximoIdioma = if (usandoIdiomaPadrao) "pt" else "en"
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(proximoIdioma)
            )
        }
    }
}