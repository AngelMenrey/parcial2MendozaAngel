package com.example.examenparcial2mendozareyesangelemanuel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VideoActivity : AppCompatActivity() {
    private val REQUEST_VIDEO_CAPTURE = 1
    private var videoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val grabarVideoBtn = findViewById<Button>(R.id.grabarVideoBtn)
        val reproducirVideoBtn = findViewById<Button>(R.id.reproducirVideoBtn)

        grabarVideoBtn.setOnClickListener {
            dispatchTakeVideoIntent()
        }

        reproducirVideoBtn.setOnClickListener {
            if (videoUri != null) {
                val intent = Intent(Intent.ACTION_VIEW, videoUri)
                intent.setDataAndType(videoUri, "video/*")
                startActivity(intent)
            } else {
                Toast.makeText(this, "No hay video para reproducir", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            videoUri = data?.data
            Toast.makeText(this, "Video grabado con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al grabar el video", Toast.LENGTH_SHORT).show()
        }
    }
}