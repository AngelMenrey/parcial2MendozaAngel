package com.example.examenparcial2mendozareyesangelemanuel

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CatalagoActivity : AppCompatActivity() {
    private val bicicletas = mutableListOf<Bicicletas>()
    private lateinit var fotoImageView: ImageView
    private var fotoBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalago)

        val codigoInput = findViewById<EditText>(R.id.codigoInput)
        val marcaInput = findViewById<EditText>(R.id.marcaInput)
        val modeloInput = findViewById<EditText>(R.id.modeloInput)
        val anhoInput = findViewById<EditText>(R.id.anhoInput)
        fotoImageView = findViewById(R.id.fotoImageView)
        val capturarFotoBtn = findViewById<Button>(R.id.capturarFotoBtn)
        val guardarBtn = findViewById<Button>(R.id.guardarBtn)
        val buscarBtn = findViewById<Button>(R.id.buscarBtn)
        val limpiarBtn = findViewById<Button>(R.id.limpiarBtn)

        capturarFotoBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1)
        }

        guardarBtn.setOnClickListener {
            if (codigoInput.text.isEmpty() || marcaInput.text.isEmpty() || modeloInput.text.isEmpty() || anhoInput.text.isEmpty() || fotoBitmap == null) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                val bicicleta = Bicicletas(
                    codigoInput.text.toString(),
                    marcaInput.text.toString(),
                    modeloInput.text.toString(),
                    anhoInput.text.toString().toInt(),
                    fotoBitmap
                )
                bicicletas.add(bicicleta)
                Toast.makeText(this, "Bicicleta guardada", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            }
        }

        buscarBtn.setOnClickListener {
            val codigo = codigoInput.text.toString()
            val bicicletaEncontrada = bicicletas.find { it.id == codigo }

            if (bicicletaEncontrada != null) {
                marcaInput.setText(bicicletaEncontrada.marca)
                modeloInput.setText(bicicletaEncontrada.modelo)
                anhoInput.setText(bicicletaEncontrada.anho.toString())
                fotoImageView.setImageBitmap(bicicletaEncontrada.foto)
                Toast.makeText(this, "Bicicleta encontrada: ${bicicletaEncontrada.marca}, Modelo: ${bicicletaEncontrada.modelo}, Año: ${bicicletaEncontrada.anho}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Bicicleta no encontrada", Toast.LENGTH_SHORT).show()
            }
        }

        limpiarBtn.setOnClickListener {
            limpiarCampos()
            Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val extras = data?.extras
            fotoBitmap = extras?.get("data") as Bitmap
            fotoImageView.setImageBitmap(fotoBitmap)
        } else {
            Toast.makeText(this, "Error al capturar la foto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos() {
        findViewById<EditText>(R.id.codigoInput).text.clear()
        findViewById<EditText>(R.id.marcaInput).text.clear()
        findViewById<EditText>(R.id.modeloInput).text.clear()
        findViewById<EditText>(R.id.anhoInput).text.clear()
        fotoImageView.setImageResource(android.R.color.darker_gray)
        fotoBitmap = null
    }
}