package com.example.agenda.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.databinding.ActivityCadastroContatoBinding
import com.example.agenda.model.Contato

class CadastroContatoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroContatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verifica se foi passado um contato para edição
        val contatoExistente = intent.getSerializableExtra("contato") as? Contato
        contatoExistente?.let {
            binding.editNome.setText(it.nome)
            binding.editTelefone.setText(it.telefone)
            binding.editEmail.setText(it.email)
        }

        setupListeners(contatoExistente)
    }

    private fun setupListeners(contatoExistente: Contato?) {
        binding.btnEntrar.setOnClickListener {
            val nome = binding.editNome.text.toString().trim()
            val telefone = binding.editTelefone.text.toString().trim()
            val email = binding.editEmail.text.toString().trim()

            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                val contato = Contato(
                    R.drawable.ic_launcher_foreground, // Foto padrão
                    nome,
                    telefone,
                    email
                )

                val resultIntent = Intent()
                resultIntent.putExtra("novo_contato", contato)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}
