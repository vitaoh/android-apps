package com.example.agenda.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.R
import com.example.agenda.adapter.ContatoAdapter
import com.example.agenda.databinding.ActivityMainBinding
import com.example.agenda.model.Contato

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contatos = mutableListOf<Contato>()
    private lateinit var adapter: ContatoAdapter

    private val ADD_CONTATO_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupRecyclerView()
        setupListeners()
    }

    private fun loadData() {
        contatos.addAll(
            listOf(
                Contato(R.drawable.minkey, "Minkey", "(16) 98888-7777", "minkey@email.com"),
                Contato(R.drawable.mennie, "Mennie", "(16) 97777-6666", "mennie@email.com"),
                Contato(R.drawable.patetla, "Patleta", "(16) 96666-5555", "patleta@email.com"),
                Contato(R.drawable.truco, "Truco", "(16) 98888-7777", "truco@email.com"),
                Contato(R.drawable.donaldo, "Donaldo", "(16) 97777-6666", "donaldo@email.com"),
                Contato(R.drawable.carlos, "Carlos", "(16) 96666-5555", "carlos@email.com")
            )
        )
        contatos.sortBy { it.nome }
    }

    private fun setupRecyclerView() {
        adapter = ContatoAdapter(contatos) { contato ->
            val intent = Intent(this, DetalheContatoActivity::class.java)
            intent.putExtra("contato", contato)
            startActivity(intent)
        }

        binding.recyclerViewContatos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewContatos.adapter = adapter
    }

    private fun setupListeners() {
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
                contatos.add(novoContato)
                contatos.sortBy { it.nome }
                adapter.notifyDataSetChanged()
            }
        }
    }
}
