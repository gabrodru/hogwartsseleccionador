package com.example.hogwartsseleccionador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hogwartsDB = HogwartsDatabaseHelper(this)
        val numAlumnos = hogwartsDB.countAlumnos()

        val textViewNumAlumnos: TextView = findViewById(R.id.textViewNumAlumnos)
        textViewNumAlumnos.text = numAlumnos.toString()

        // Cogemos el botón nuevo alumno y creamos el evento listener
        val btnNuevoAlumno: Button = findViewById(R.id.btnNuevoAlumno)
        btnNuevoAlumno.setOnClickListener {
            val intent = Intent(this, NuevoAlumno::class.java)
            startActivity(intent)
        }

        // Botón para acceder a los listados de Alumnos
        val btnListadoAlumnos: Button = findViewById(R.id.btnListaAlumnos)
        btnListadoAlumnos.setOnClickListener {
            val intent = Intent(this, EligeCasa::class.java)
            startActivity(intent)
        }
    }
}