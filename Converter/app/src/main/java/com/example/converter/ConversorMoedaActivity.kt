package com.example.converter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConversorMoedaActivity : AppCompatActivity() {
    companion object {
        private const val COTACAO_FIXA = 5.00
    }

    private lateinit var ivBandeiraOrigem: ImageView
    private lateinit var ivBandeiraDestino: ImageView
    private lateinit var etValor: EditText
    private lateinit var btnInverter: Button
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    private var deRealParaDolar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        ivBandeiraOrigem = findViewById(R.id.ivBandeiraOrigem)
        ivBandeiraDestino = findViewById(R.id.ivBandeiraDestino)
        etValor = findViewById(R.id.etValor)
        btnInverter = findViewById(R.id.btnInverter)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)
    }

    private fun setupListeners() {
        btnInverter.setOnClickListener { inverterMoedas() }
        btnCalcular.setOnClickListener(::calcularConversao)
    }

    private fun inverterMoedas() {
        deRealParaDolar = !deRealParaDolar

        if (deRealParaDolar) {
            ivBandeiraOrigem.setImageResource(R.drawable.bandeira_brasil)
            ivBandeiraDestino.setImageResource(R.drawable.bandeira_eua)
        } else {
            ivBandeiraOrigem.setImageResource(R.drawable.bandeira_eua)
            ivBandeiraDestino.setImageResource(R.drawable.bandeira_brasil)
        }

        etValor.text.clear()
        tvResultado.text = getString(R.string.resultado_padrao)
    }

    private fun calcularConversao(view: View) {
        val texto = etValor.text.toString()
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
        tvResultado.text = textoResultado
    }

}