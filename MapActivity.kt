package com.example.mobile_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val btnOpenMap = findViewById<Button>(R.id.btnOpenMap)

        btnOpenMap.setOnClickListener {
            // Example: Open Google Maps at Ontario Tech University
            val gmmIntentUri = Uri.parse("geo:0,0?q=Ontario+Tech+University")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}
