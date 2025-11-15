package com.example.project

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityUtilityTrackingBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class UtilityTrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUtilityTrackingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUtilityTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Toolbar back button
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ Dummy data
        val gasUsage = listOf(20f, 30f, 25f, 40f, 35f)
        val electricityUsage = listOf(50f, 60f, 55f, 70f, 65f)
        val waterUsage = listOf(15f, 20f, 18f, 22f, 19f)

        setupBarChart(binding.chartGas, gasUsage, "Gas Usage")
        setupBarChart(binding.chartElectricity, electricityUsage, "Electricity Usage")
        setupBarChart(binding.chartWater, waterUsage, "Water Usage")
    }

    private fun setupBarChart(chart: com.github.mikephil.charting.charts.BarChart, values: List<Float>, label: String) {
        val entries = values.mapIndexed { index, value -> BarEntry(index.toFloat(), value) }
        val dataSet = BarDataSet(entries, label)
        dataSet.color = android.graphics.Color.parseColor("#3F51B5") // Blue bars
        val barData = BarData(dataSet)
        chart.data = barData
        chart.description.isEnabled = false
        chart.animateY(1000)
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
