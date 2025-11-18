package com.example.project

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityUtilityTrackingBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.text.SimpleDateFormat
import java.util.*

class UtilityTrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUtilityTrackingBinding
    private val labelFormat = SimpleDateFormat("MMM d", Locale.getDefault())
    private var currentWeekStart: Calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUtilityTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Toolbar back button
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ Initial chart load
        loadChartsForWeek(currentWeekStart)

        // ✅ Previous Week button
        binding.btnPrevWeek.setOnClickListener {
            currentWeekStart.add(Calendar.WEEK_OF_YEAR, -1)
            loadChartsForWeek(currentWeekStart)
        }

        // ✅ Next Week button (but don’t go past current week)
        binding.btnNextWeek.setOnClickListener {
            val today = Calendar.getInstance().apply {
                set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            }
            if (currentWeekStart.before(today)) {
                currentWeekStart.add(Calendar.WEEK_OF_YEAR, 1)
                loadChartsForWeek(currentWeekStart)
            } else {
                Toast.makeText(this, "You’re already viewing the current week.", Toast.LENGTH_SHORT).show()
            }
        }

        // ✅ Current Week button (jump back instantly)
        binding.btnCurrentWeek.setOnClickListener {
            currentWeekStart = Calendar.getInstance().apply {
                set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
            }
            loadChartsForWeek(currentWeekStart)
        }
    }

    private fun loadChartsForWeek(weekStart: Calendar) {
        val label = "Week of ${labelFormat.format(weekStart.time)}"
        binding.weekLabel.text = label

        // ✅ Random usage data per week (seeded by week number for consistency)
        val random = Random(weekStart.get(Calendar.WEEK_OF_YEAR).toLong())
        val gasUsage = List(5) { 15 + random.nextInt(30) }.map { it.toFloat() }
        val electricityUsage = List(5) { 40 + random.nextInt(40) }.map { it.toFloat() }
        val waterUsage = List(5) { 10 + random.nextInt(15) }.map { it.toFloat() }

        setupBarChart(binding.chartGas, gasUsage, "Gas Usage")
        setupBarChart(binding.chartElectricity, electricityUsage, "Electricity Usage")
        setupBarChart(binding.chartWater, waterUsage, "Water Usage")
    }

    private fun setupBarChart(chart: com.github.mikephil.charting.charts.BarChart, values: List<Float>, label: String) {
        val entries = values.mapIndexed { index, value -> BarEntry(index.toFloat(), value) }
        val dataSet = BarDataSet(entries, label)
        dataSet.color = android.graphics.Color.parseColor("#3F51B5")
        val barData = BarData(dataSet)
        chart.data = barData
        chart.description.isEnabled = false
        chart.animateY(800)
        chart.invalidate()
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
