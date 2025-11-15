package com.example.project

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.databinding.ActivityLocalServiceAlertsBinding

class LocalServiceAlertsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalServiceAlertsBinding
    private lateinit var adapter: AlertAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalServiceAlertsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Toolbar back button
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Dummy alerts
        val alerts = listOf(
            Alert("North Region", "Water service maintenance scheduled for Monday."),
            Alert("South Region", "Power outage expected Tuesday 2–4 PM."),
            Alert("East Region", "Gas line inspection Wednesday morning."),
            Alert("West Region", "Road closure near Main Street this weekend.")
        )

        adapter = AlertAdapter(alerts)
        binding.recyclerAlerts.layoutManager = LinearLayoutManager(this)
        binding.recyclerAlerts.adapter = adapter
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
