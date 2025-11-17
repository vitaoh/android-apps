package com.example.agenda.model

import java.io.Serializable

data class Contato(
    val foto: String? = null,
    val nome: String,
    val telefone: String,
    val email: String
) : Serializable
