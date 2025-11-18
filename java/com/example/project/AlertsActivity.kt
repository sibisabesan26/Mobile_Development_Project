package com.example.project

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlertsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlertAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerts)

        // ✅ Toolbar setup
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ RecyclerView setup
        recyclerView = findViewById(R.id.recyclerAlerts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ✅ Generate random alerts
        val allAlerts = listOf(
            Alert("Etobicoke", "Garbage collection delayed due to icy roads."),
            Alert("Scarborough", "Toronto Hydro reports power outage near Morningside."),
            Alert("Downtown", "TTC Line 1 experiencing delays near Union Station."),
            Alert("North York", "Water main maintenance scheduled for Thursday."),
            Alert("Pickering", "Greenwood Conservation Area closed for trail repairs."),
            Alert("West Rouge", "Fire rescue at Port Union Rd. Avoid the area."),
            Alert("Toronto", "City Council approves new recycling program."),
            Alert("East York", "Snowfall warning in effect. Drive with caution.")
        )

        val randomSubset = allAlerts.shuffled().take((1..3).random())
        adapter = AlertAdapter(randomSubset)
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
