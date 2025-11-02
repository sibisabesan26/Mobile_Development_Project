package com.example.mobile_project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_project.data.AppDatabaseHelper

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val dbHelper = AppDatabaseHelper(this)

        val titleField = findViewById<EditText>(R.id.etTaskTitle)
        val descField = findViewById<EditText>(R.id.etTaskDescription)
        val addButton = findViewById<Button>(R.id.btnAddTask)

        addButton.setOnClickListener {
            val title = titleField.text.toString().trim()
            val description = descField.text.toString().trim()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val success = dbHelper.insertTask(title, description)
                if (success) {
                    Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to add task", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
