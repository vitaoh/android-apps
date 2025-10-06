package com.example.perfilapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.perfilapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // findViewById é a forma antiga de acessar componentes da interface no Android,
    // exigindo que você busque manualmente os elementos pelo ID. Já o View Binding
    // é uma forma moderna e segura que gera automaticamente uma classe com referências
    // diretas aos elementos do layout, eliminando a necessidade de cast e tornando
    // o código mais limpo, legível e com menos chances de erro. Por isso, o View Binding
    // é a abordagem recomendada atualmente.

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val KEY_NAME = "key_name"
        const val KEY_EMAIL = "key_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restaura os valores salvos (TextViews)
        savedInstanceState?.let {
            val savedName = it.getString(KEY_NAME)
            val savedEmail = it.getString(KEY_EMAIL)

            binding.tvClientName.text = savedName
            binding.tvClientEmail.text = savedEmail
        }

        // Botão "Mudar"
        binding.btnChange.setOnClickListener {
            val newName = binding.typeName.text.toString().trim()
            val newEmail = binding.typeEmail.text.toString().trim()

            if (newName.isNotEmpty()) {
                binding.tvClientName.text = newName
            }

            if (newEmail.isNotEmpty()) {
                binding.tvClientEmail.text = newEmail
            }
        }

        // Botão "Tema"
        binding.btnTheme.setOnClickListener {
            val isNight = AppCompatDelegate.getDefaultNightMode() ==
                    AppCompatDelegate.MODE_NIGHT_YES
            val mode = if (isNight) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            AppCompatDelegate.setDefaultNightMode(mode)
        }

        // Botão "Idioma"
        binding.btnLang.setOnClickListener {
            val idiomaAtual = AppCompatDelegate.getApplicationLocales()
            val usandoIdiomaPadrao = !idiomaAtual.isEmpty && idiomaAtual[0]?.language == "en"
            val proximoIdioma = if (usandoIdiomaPadrao) "pt" else "en"
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(proximoIdioma)
            )
        }
    }

    // Salva os valores dos TextViews antes da Activity ser destruída
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val currentName = binding.tvClientName.text.toString()
        val currentEmail = binding.tvClientEmail.text.toString()

        outState.putString(KEY_NAME, currentName)
        outState.putString(KEY_EMAIL, currentEmail)
    }
}
