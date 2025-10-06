package com.example.catalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catalog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lista de nomes dos cursos
        val nomes = listOf(
            getString(R.string.nome_tsi),
            getString(R.string.nome_eng),
            getString(R.string.nome_ads),
            getString(R.string.nome_mat)
        )

        // Mapa com as descrições
        val descricoes = mapOf(
            getString(R.string.nome_tsi) to getString(R.string.desc_tsi),
            getString(R.string.nome_eng) to getString(R.string.desc_eng),
            getString(R.string.nome_ads) to getString(R.string.desc_ads),
            getString(R.string.nome_mat) to getString(R.string.desc_mat)
        )

        // Adapter para o ListView
        val adapter = android.widget.ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            nomes
        )

        binding.lista.adapter = adapter

        // Clique nos itens da lista
        binding.lista.setOnItemClickListener { _, _, position, _ ->
            val nomeCurso = nomes[position]
            val descricao = descricoes[nomeCurso]
            binding.textViewDescricao.text = descricao
        }
    }
}
