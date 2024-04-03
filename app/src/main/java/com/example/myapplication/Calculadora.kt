package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R.id

class Calculadora : AppCompatActivity() {

    private lateinit var tvResultado: TextView
    private var operacionPendiente: String = ""
    private var numeroAnterior: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        tvResultado = findViewById(id.tvResultado)

        // Configurar los listeners de los botones numéricos
        val numeros = listOf(
            id.btnCero, id.btnUno, id.btnDos, id.btnTres, id.btnCuatro,
            id.btnCinco, id.btnSeis, id.btnSiete, id.btnOcho, id.btnNueve, id.btnDecimal)

        numeros.forEach { id ->
            findViewById<Button>(id).setOnClickListener { agregarDigito((it as Button).text.toString()) }
        }

        // Configurar los listeners de los botones de operación
        val operaciones = listOf(id.btnSumar, id.btnRestar, id.btnMultiplicacion, id.btnDivision, id.btnPorcentaje)

        operaciones.forEach { id ->
            findViewById<Button>(id).setOnClickListener { operar((it as Button).text.toString()) }
        }

        // Configurar el listener del botón igual
        findViewById<Button>(id.btnIgual).setOnClickListener { calcularResultado() }

        // Configurar el listener del botón borrar
        findViewById<Button>(id.btnBorrar).setOnClickListener { borrarUltimoDigito() }

        // Configurar el listener del botón borrar todo
        findViewById<Button>(id.btnBorrarTodo).setOnClickListener { borrarTodo() }
    }

    private fun agregarDigito(digito: String) {
        val textoActual = tvResultado.text.toString()
        if (textoActual == "0") {
            tvResultado.text = digito
        } else {
            tvResultado.append(digito)
        }
    }

    private fun operar(op: String) {
        operacionPendiente = op
        numeroAnterior = tvResultado.text.toString().toDouble()
        tvResultado.text = ""
    }

    private fun calcularResultado() {
        val numeroActual = tvResultado.text.toString().toDouble()
        var resultado = 0.0

        when (operacionPendiente) {
            "+" -> resultado = numeroAnterior + numeroActual
            "-" -> resultado = numeroAnterior - numeroActual
            "*" -> resultado = numeroAnterior * numeroActual
            "/" -> resultado = numeroAnterior / numeroActual
            "%" -> resultado = numeroAnterior * (numeroActual / 100)
        }

        // Verificar si el resultado es un número entero
        if (resultado % 1 == 0.0) {
            // Mostrar el resultado como entero
            tvResultado.text = resultado.toInt().toString()
        } else {
            // Mostrar el resultado con decimales
            tvResultado.text = resultado.toString()
        }
    }

    private fun borrarUltimoDigito() {
        val textoActual = tvResultado.text.toString()
        if (textoActual.length > 1) {
            tvResultado.text = textoActual.substring(0, textoActual.length - 1)
        } else {
            tvResultado.text = "0"
        }
    }

    private fun borrarTodo() {
        tvResultado.text = "0"
        operacionPendiente = ""
        numeroAnterior = 0.0
    }
}
