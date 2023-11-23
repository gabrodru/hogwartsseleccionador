package com.example.hogwartsseleccionador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cogemos el botón nuevo alumno y creamos el evento listener
        val btnNuevoAlumno: Button = findViewById(R.id.btnNuevoAlumno)
        btnNuevoAlumno.setOnClickListener {
            val intent = Intent(this, NuevoAlumno::class.java)
            startActivity(intent)
        }

        // Botón para acceder a los listados de Alumnos
        val btnListaAlumnos: Button = findViewById(R.id.btnListaAlumnos)
        btnListaAlumnos.setOnClickListener {
            val hogwartsDB = HogwartsDatabaseHelper(this)
            if (!hogwartsDB.hayAlumnos()) {
                Toast.makeText(this, "Primero debes meter un alumno", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, EligeCasa::class.java)
            startActivity(intent)
        }

        // Botón para borrar los Alumnos
        val btnBorrarAlumnos: ImageView = findViewById(R.id.imageBorrar)
        btnBorrarAlumnos.setOnClickListener {
            val dialogoBuilder = AlertDialog.Builder(this)
            dialogoBuilder.setTitle("Confirmación")
            dialogoBuilder.setMessage("¿Estás seguro de que quieres borrar todos los alumnos?")


            dialogoBuilder.setPositiveButton("Borrar") { dialog, which ->
                val hogwartsDB = HogwartsDatabaseHelper(this)
                hogwartsDB.deleteAllAlumnos()
                Toast.makeText(this, "Todos los alumnos han sido borrados", Toast.LENGTH_SHORT)
                    .show()
            }


            dialogoBuilder.setNegativeButton("Cancelar") { dialog, which ->

            }

            dialogoBuilder.show()
        }
    }
}