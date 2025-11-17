package com.example.agenda.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.agenda.R
import com.example.agenda.databinding.ActivityDetalheContatoBinding
import com.example.agenda.model.Contato

class DetalheContatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheContatoBinding
    private lateinit var contato: Contato
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData() {
        contato = intent.getSerializableExtra("contato") as Contato
    }

    private fun setupListeners() {
        binding.btnLigar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:${contato.telefone}".toUri()
            startActivity(intent)
        }
        binding.btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = "mailto:${contato.email}".toUri()
            startActivity(intent)
        }
        binding.btnVoltar.setOnClickListener {
            Toast.makeText(this, "Voltar", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupViews() {
        binding.tvNome.text = contato.nome
        binding.tvTelefone.text = contato.telefone
        binding.tvEmail.text = contato.email

        Glide.with(this)
            .load(contato.foto)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.imgFoto)
    }
}