package com.example.examenparcial2mendozareyesangelemanuel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_catalago -> {
                startActivity(Intent(this, CatalagoActivity::class.java))
                true
            }
            R.id.menu_video -> {
                startActivity(Intent(this, VideoActivity::class.java))
                true
            }
            R.id.menu_sensor -> {
                startActivity(Intent(this, SensorActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}