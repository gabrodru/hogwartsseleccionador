package com.example.hogwartsseleccionador

import CasaHogwarts
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
class EligeCasa : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elige_casa)

        // Mostramos el número de alumnos por cada casa cargados en la BBDD
        val txtNumGriffindor: TextView = findViewById(R.id.txtNumGriffindorf)
        val txtNumSlytherin: TextView = findViewById(R.id.txtNumSlytherin)
        val txtNumHufflepuff: TextView = findViewById(R.id.txtNumHufflepuff)
        val txtNumRavenclaw: TextView = findViewById(R.id.txtNumRavenclaw)

        // Actualizar conteo de alumnos y color del texto si es necesario
        updateStudentCount(txtNumGriffindor, CasaHogwarts.CASA_GRIFFINDORF)
        updateStudentCount(txtNumSlytherin, CasaHogwarts.CASA_SLYTHERIN)
        updateStudentCount(txtNumHufflepuff, CasaHogwarts.CASA_HUFFLEPLUFF)
        updateStudentCount(txtNumRavenclaw, CasaHogwarts.CASA_RAVENCLAW)

        // Botón y acción para Griffindor
        val btnGriffindor: Button = findViewById(R.id.btnVerGriffindorf)
        btnGriffindor.setOnClickListener {
            val intent = Intent(this, ListadoCasaHogwarts::class.java)
            intent.putExtra("casa", CasaHogwarts.CASA_GRIFFINDORF)
            startActivity(intent)
        }

        // Botón y acción para Slytherin
        val btnSlytherin: Button = findViewById(R.id.btnVerSlytherin)
        btnSlytherin.setOnClickListener {
            val intent = Intent(this, ListadoCasaHogwarts::class.java)
            intent.putExtra("casa", CasaHogwarts.CASA_SLYTHERIN)
            startActivity(intent)
        }

        // Botón y acción para Hufflepuff
        val btnHufflepuff: Button = findViewById(R.id.btnVerHufflepuff)
        btnHufflepuff.setOnClickListener {
            val intent = Intent(this, ListadoCasaHogwarts::class.java)
            intent.putExtra("casa", CasaHogwarts.CASA_HUFFLEPLUFF)
            startActivity(intent)
        }

        // Botón y acción para Ravenclaw
        val btnRavenclaw: Button = findViewById(R.id.btnVerRavenclaw)
        btnRavenclaw.setOnClickListener {
            val intent = Intent(this, ListadoCasaHogwarts::class.java)
            intent.putExtra("casa", CasaHogwarts.CASA_RAVENCLAW)
            startActivity(intent)
        }

        val btnAtras: ImageButton = findViewById(R.id.btnAtras)
        btnAtras.setOnClickListener {
            // Finalizar la actividad
            finish()
        }

        val btnHome: ImageView = findViewById(R.id.imageHome)
        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun updateStudentCount(textView: TextView, house: String) {
        val blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)
        var hogwartsDB: HogwartsDatabaseHelper = HogwartsDatabaseHelper(this)
        val count = hogwartsDB.getNumeroAlumnos(house)
        textView.text = "$count alumnos"
        if (count > 0) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.black))
            textView.setTypeface(null, Typeface.BOLD)
            textView.startAnimation(blinkAnimation)
            return
        }

    }
}