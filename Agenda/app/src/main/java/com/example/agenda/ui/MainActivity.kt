package com.example.agenda.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agenda.R
import com.example.agenda.adapter.ContatoAdapter
import com.example.agenda.databinding.ActivityMainBinding
import com.example.agenda.model.Contato

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contatos: List<Contato>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }

    fun loadData() {
        contatos = listOf(
            Contato(
                R.drawable.minkey,
                "Minkey",
                "(16) 98888-7777",
                "minkey@email.com"
            ),
            Contato(
                R.drawable.mennie,
                "Mennie",
                "(16) 97777-6666",
                "mennie@email.com"
            ),
            Contato(
                R.drawable.patetla,
                "Patleta",
                "(16) 96666-5555",
                "patleta@email.com"
            ),
            Contato(
                R.drawable.truco,
                "Truco",
                "(16) 98888-7777",
                "truco@email.com"
            ),
            Contato(
                R.drawable.donaldo,
                "Donaldo",
                "(16) 97777-6666",
                "donaldo@email.com"
            ),
            Contato(
                R.drawable.carlos,
                "Carlos",
                "(16) 96666-5555",
                "carlos@email.com"
            )
        ).sortedBy { it.nome }
    }

    fun setupViews() {
        val adapter = ContatoAdapter(this, contatos)
        binding.listViewContatos.adapter = adapter
    }

    private fun setupListeners() {
        binding.listViewContatos.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalheContatoActivity::class.java)
            intent.putExtra("contato", contatos[position])
            startActivity(intent)
        }
    }
}
