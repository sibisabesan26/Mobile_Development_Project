package com.example.mobile_project

import android.content.Intent
import android.provider.MediaStore
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val btnLaunchCamera = findViewById<Button>(R.id.btnLaunchCamera)

        btnLaunchCamera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (cameraIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
            } else {
                Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
