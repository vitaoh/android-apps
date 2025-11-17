package com.example.agenda.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.agenda.R
import com.example.agenda.databinding.ActivityCadastroContatoBinding
import com.example.agenda.model.Contato

class CadastroContatoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroContatoBinding
    private var urlFotoSelecionada: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {

        // 👉 Agora só usa URL ao clicar na foto
        binding.imgFoto.setOnClickListener {
            mostrarDialogParaUrl()
        }

        binding.btnEntrar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()

            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contato = Contato(
                foto = urlFotoSelecionada,
                nome = nome,
                telefone = telefone,
                email = email
            )

            val resultIntent = Intent()
            resultIntent.putExtra("novo_contato", contato)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    // 👉 Exibe diálogo para o usuário colar a URL
    private fun mostrarDialogParaUrl() {
        val editText = EditText(this)
        editText.hint = "Cole a URL da imagem"

        AlertDialog.Builder(this)
            .setTitle("URL da Imagem")
            .setView(editText)
            .setPositiveButton("Carregar") { _, _ ->
                val url = editText.text.toString().trim()

                if (url.isNotEmpty()) {
                    carregarImagemDaWeb(url)
                } else {
                    Toast.makeText(this, "Digite uma URL válida!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    // 👉 Carrega a imagem digitada
    private fun carregarImagemDaWeb(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.imgFoto)

        urlFotoSelecionada = url
    }
}
