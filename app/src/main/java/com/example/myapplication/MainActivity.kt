package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var etNombre: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Ciclo de vida", "OnCreate" )
        etNombre = findViewById(R.id.etNombrePersona)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Ciclo de vida", "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Ciclo de vida", "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Ciclo de vida", "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Ciclo de vida", "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Ciclo de vida", "OnDestroy")
    }
}