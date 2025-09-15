package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    private var input = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

        val buttons = listOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9),
            findViewById<Button>(R.id.btnDot),
            findViewById<Button>(R.id.btnPlus),
            findViewById<Button>(R.id.btnMinus),
            findViewById<Button>(R.id.btnMultiply),
            findViewById<Button>(R.id.btnDivide),
            findViewById<Button>(R.id.btnOpenParen),
            findViewById<Button>(R.id.btnCloseParen)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                input += button.text
                tvInput.text = input
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            input = ""
            tvInput.text = input
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                tvInput.text = input
            }
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateResult() {
        try {
            val expressionStr = input.replace("÷", "/").replace("×", "*").replace("−", "-")
            val expression = ExpressionBuilder(expressionStr).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                tvInput.text = longResult.toString()
                input = longResult.toString()
            } else {
                tvInput.text = result.toString()
                input = result.toString()
            }
        } catch (e: Exception) {
            tvInput.text = "Error"
            input = ""
        }
    }
}

