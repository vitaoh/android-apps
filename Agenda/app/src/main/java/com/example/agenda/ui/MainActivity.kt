package com.example.agenda.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.adapter.ContatoAdapter
import com.example.agenda.databinding.ActivityMainBinding
import com.example.agenda.model.Contato

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contatos: List<Contato>

    private val ADD_CONTATO_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData() {
        contatos = listOf(
            Contato(R.drawable.minkey, "Minkey", "(16) 98888-7777", "minkey@email.com"),
            Contato(R.drawable.mennie, "Mennie", "(16) 97777-6666", "mennie@email.com"),
            Contato(R.drawable.patetla, "Patleta", "(16) 96666-5555", "patleta@email.com"),
            Contato(R.drawable.truco, "Truco", "(16) 98888-7777", "truco@email.com"),
            Contato(R.drawable.donaldo, "Donaldo", "(16) 97777-6666", "donaldo@email.com"),
            Contato(R.drawable.carlos, "Carlos", "(16) 96666-5555", "carlos@email.com")
        ).sortedBy { it.nome }
    }

    private fun setupViews() {
        val adapter = ContatoAdapter(this, contatos)
        binding.listViewContatos.adapter = adapter
    }

    private fun setupListeners() {
        // Abrir Detalhes do Contato
        binding.listViewContatos.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, CadastroContatoActivity::class.java)
            intent.putExtra("contato", contatos[position]) // Serializable
            startActivity(intent)
        }

        // Adicionar novo contato
        binding.fabAddContato.setOnClickListener {
            val intent = Intent(this, CadastroContatoActivity::class.java)
            startActivityForResult(intent, ADD_CONTATO_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_CONTATO_REQUEST && resultCode == Activity.RESULT_OK) {
            val novoContato = data?.getSerializableExtra("novo_contato") as? Contato
            if (novoContato != null) {
                // Atualiza lista de contatos
                contatos = contatos.toMutableList().apply { add(novoContato) }.sortedBy { it.nome }
                val adapter = ContatoAdapter(this, contatos)
                binding.listViewContatos.adapter = adapter
            }
        }
    }
}
