package com.example.agenda.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agenda.adapter.ContatoAdapter
import com.example.agenda.databinding.ActivityMainBinding
import com.example.agenda.model.Contato

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContatoAdapter
    private val contatos = mutableListOf<Contato>()

    private val launcherCadastroContato =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                val novoContato = result.data?.getSerializableExtra("novo_contato") as? Contato
                if (novoContato != null) {
                    contatos.add(novoContato)
                    contatos.sortBy { it.nome }
                    adapter.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupListeners()
    }

    private fun setupRecycler() {
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
            launcherCadastroContato.launch(intent)
        }
    }
}
