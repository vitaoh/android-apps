package com.example.agenda.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agenda.R
import com.example.agenda.databinding.ItemContatoBinding
import com.example.agenda.model.Contato

class ContatoAdapter(
    private val contatos: List<Contato>,
    private val onClick: (Contato) -> Unit
) : RecyclerView.Adapter<ContatoAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemContatoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contato: Contato) {
            with(binding) {

                Glide.with(root.context)
                    .load(contato.foto)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imgFoto)

                tvNome.text = contato.nome
                tvTelefone.text = contato.telefone
                tvEmail.text = contato.email

                root.setOnClickListener { onClick(contato) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContatoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contatos[position])
    }

    override fun getItemCount(): Int = contatos.size
}
