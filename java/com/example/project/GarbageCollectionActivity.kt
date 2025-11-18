package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class GarbageCollectionActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter
    private lateinit var btnNextWeek: Button
    private lateinit var btnPrevWeek: Button
    private lateinit var weekLabel: TextView

    private val dateFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
    private val labelFormat = SimpleDateFormat("MMM d", Locale.getDefault())

    private var currentWeekStart: Calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garbage_collection)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerSchedules)
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnNextWeek = findViewById(R.id.btnNextWeek)
        btnPrevWeek = findViewById(R.id.btnPrevWeek)
        weekLabel = findViewById(R.id.weekLabel)

        loadSchedule(currentWeekStart)

        btnNextWeek.setOnClickListener {
            currentWeekStart.add(Calendar.WEEK_OF_YEAR, 1)
            loadSchedule(currentWeekStart)
        }

        btnPrevWeek.setOnClickListener {
            val today = Calendar.getInstance().apply {
                set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            }
            if (currentWeekStart.after(today)) {
                currentWeekStart.add(Calendar.WEEK_OF_YEAR, -1)
                loadSchedule(currentWeekStart)
            }
        }
    }

    private fun loadSchedule(weekStart: Calendar) {
        val schedules = mutableListOf<Schedule>()
        val temp = weekStart.clone() as Calendar

        weekLabel.text = "Week of ${labelFormat.format(temp.time)}"

        val types = listOf(
            "General Waste",
            "Recycling",
            "Yard Waste",
            "General Waste",
            "Recycling",
            "No Collection",
            "No Collection"
        )

        for (i in 0..6) {
            val dayLabel = dateFormat.format(temp.time)
            schedules.add(Schedule(dayLabel, types[i]))
            temp.add(Calendar.DAY_OF_YEAR, 1)
        }

        adapter = ScheduleAdapter(schedules) { schedule ->
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("day", schedule.day)
            intent.putExtra("type", schedule.type)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }

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
