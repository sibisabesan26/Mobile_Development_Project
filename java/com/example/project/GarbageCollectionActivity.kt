package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MapActivity

class GarbageCollectionActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garbage_collection)

        // ✅ Wire up Toolbar as ActionBar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // ✅ Show back button in ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerSchedules)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val schedules = listOf(
            Schedule("Monday", "General Waste"),
            Schedule("Tuesday", "Recycling"),
            Schedule("Wednesday", "Yard Waste"),
            Schedule("Thursday", "General Waste"),
            Schedule("Friday", "Recycling"),
            Schedule("Saturday", "No Collection"),
            Schedule("Sunday", "No Collection")
        )

        // ✅ Pass onItemClick lambda to adapter
        adapter = ScheduleAdapter(schedules) { schedule ->
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("day", schedule.day)
            intent.putExtra("type", schedule.type)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

    // ✅ Handle back button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
