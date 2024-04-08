package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityCalculadoraBinding

class Calculadora : AppCompatActivity() {

    private lateinit var binding: ActivityCalculadoraBinding
    private var operacionPendiente: String = ""
    private var numeroAnterior: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar los listeners de los botones numéricos
        val numeros = listOf(
            binding.btnCero, binding.btnUno, binding.btnDos, binding.btnTres, binding.btnCuatro,
            binding.btnCinco, binding.btnSeis, binding.btnSiete, binding.btnOcho, binding.btnNueve, binding.btnDecimal)

        numeros.forEach { button ->
            button.setOnClickListener { agregarDigito((it as Button).text.toString()) }
        }

        // Configurar los listeners de los botones de operación
        val operaciones = listOf(binding.btnSumar, binding.btnRestar, binding.btnMultiplicacion, binding.btnDivision, binding.btnPorcentaje)

        operaciones.forEach { button ->
            button.setOnClickListener { operar((it as Button).text.toString()) }
        }

        // Configurar el listener del botón igual
        binding.btnIgual.setOnClickListener { calcularResultado() }

        // Configurar el listener del botón borrar
        binding.btnBorrar.setOnClickListener { borrarUltimoDigito() }

        // Configurar el listener del botón borrar todo
        binding.btnBorrarTodo.setOnClickListener { borrarTodo() }
    }

    private fun agregarDigito(digito: String) {
        val textoActual = binding.tvResultado.text.toString()
        if (textoActual == "0") {
            binding.tvResultado.text = digito
        } else {
            binding.tvResultado.append(digito)
        }
    }

    private fun operar(op: String) {
        operacionPendiente = op
        numeroAnterior = binding.tvResultado.text.toString().toDouble()
        binding.tvResultado.text = ""
    }

    private fun calcularResultado() {
        val numeroActual = binding.tvResultado.text.toString().toDouble()
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
            binding.tvResultado.text = resultado.toInt().toString()
        } else {
            // Mostrar el resultado con decimales
            binding.tvResultado.text = resultado.toString()
        }
    }

    private fun borrarUltimoDigito() {
        val textoActual = binding.tvResultado.text.toString()
        if (textoActual.length > 1) {
            binding.tvResultado.text = textoActual.substring(0, textoActual.length - 1)
        } else {
            binding.tvResultado.text = "0"
        }
    }

    private fun borrarTodo() {
        binding.tvResultado.text = "0"
        operacionPendiente = ""
        numeroAnterior = 0.0
    }
}
