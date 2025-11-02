package com.example.mobile_project

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_project.data.AppDatabaseHelper
import com.example.mobile_project.models.Task

class TaskListActivity : AppCompatActivity() {

    private lateinit var dbHelper: AppDatabaseHelper
    private lateinit var taskList: MutableList<Task>
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        // Enable back button in the ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Your Tasks"

        // Load tasks from database
        dbHelper = AppDatabaseHelper(this)
        taskList = dbHelper.getAllTasks().toMutableList()

        // Set up ListView with custom adapter
        val listView = findViewById<ListView>(R.id.lvTasks)
        adapter = TaskAdapter(this, taskList, dbHelper)
        listView.adapter = adapter
    }

    // Handle back button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
