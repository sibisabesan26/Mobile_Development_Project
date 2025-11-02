package com.example.mobile_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dashboard"

        val btnViewTasks = findViewById<Button>(R.id.btnViewTasks)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)
        val btnMap = findViewById<Button>(R.id.btnMap)
        val btnCamera = findViewById<Button>(R.id.btnCamera)

        btnViewTasks.setOnClickListener {
            startActivity(Intent(this, TaskListActivity::class.java))
        }

        btnAddTask.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

        btnMap.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        btnCamera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
