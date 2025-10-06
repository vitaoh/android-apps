package com.example.converter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.converter.databinding.ActivityMainBinding

class ConversorMoedaActivity : AppCompatActivity() {

    companion object {
        private const val COTACAO_FIXA = 5.00
    }

    private lateinit var binding: ActivityMainBinding
    private var deRealParaDolar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnInverter.setOnClickListener { inverterMoedas() }
        binding.btnCalcular.setOnClickListener(::calcularConversao)
    }

    private fun inverterMoedas() {
        deRealParaDolar = !deRealParaDolar

        if (deRealParaDolar) {
            binding.ivBandeiraOrigem.setImageResource(R.drawable.bandeira_brasil)
            binding.ivBandeiraDestino.setImageResource(R.drawable.bandeira_eua)
        } else {
            binding.ivBandeiraOrigem.setImageResource(R.drawable.bandeira_eua)
            binding.ivBandeiraDestino.setImageResource(R.drawable.bandeira_brasil)
        }

        binding.etValor.text.clear()
        binding.tvResultado.text = getString(R.string.resultado_padrao)
    }

    private fun calcularConversao(view: View) {
        val texto = binding.etValor.text.toString()
        val valor = texto.toDoubleOrNull()

        if (valor == null) {
            Toast.makeText(this, getString(R.string.erro_valor_invalido), Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = if (deRealParaDolar) {
            valor / COTACAO_FIXA
        } else {
            valor * COTACAO_FIXA
        }

        val simbolo = if (deRealParaDolar) "US$" else "R$"
        val textoResultado = "Resultado: $simbolo %.2f".format(resultado)
        binding.tvResultado.text = textoResultado
    }
}
