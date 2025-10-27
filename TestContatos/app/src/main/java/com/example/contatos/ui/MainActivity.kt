package com.example.contatos.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.contatos.R
import com.example.contatos.databinding.ActivityCadastroContatoBinding
import com.example.contatos.model.Contato

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroContatoBinding
    private lateinit var contato: Contato
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onPause() {
        super.onPause()
        contato = Contato(
            nome = binding.edtNome.text.toString(),
            telefone = binding.edtTelefone.text.toString(),
            email = binding.edtEmail.text.toString(),
            foto = R.drawable.ic_launcher_foreground
        )
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("contato", contato)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        contato = savedInstanceState.getSerializable("contato") as Contato
        setupViews()
    }
    private fun setupViews() {
        binding.edtNome.setText(contato.nome)
        binding.edtTelefone.setText(contato.telefone)
        binding.edtEmail.setText(contato.email)
    }
}

//class MainActivity : AppCompatActivity() {
//    companion object {
//        private const val TAG = "CicloDeVida"
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "onCreate chamado")
//        setContentView(R.layout.activity_main)
//    }
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "onStart chamado")
//    }
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "onResume chamado")
//    }
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "onPause chamado")
//    }
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "onStop chamado")
//    }
//    override fun onRestart() {
//        super.onRestart()
//        Log.d(TAG, "onRestart chamado")
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "onDestroy chamado")
//    }
//}

