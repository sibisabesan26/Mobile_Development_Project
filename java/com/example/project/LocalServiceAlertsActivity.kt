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
            Alert(
                "Scarborough – Water Service Maintenance",
                "Routine maintenance on underground pipelines will take place Monday morning to improve water pressure and reliability. Residents in Scarborough may experience reduced water flow or temporary shutoffs. Schools and small businesses in the area are also expected to be affected. Estimated duration: 9 AM – 1 PM."
            ),
            Alert(
                "Etobicoke – Planned Power Outage",
                "A scheduled power outage is expected Tuesday between 2–4 PM due to grid upgrades. Utility crews will be replacing transformers and inspecting high‑voltage lines. Apartment complexes, local shops, and households in Etobicoke will be impacted. Backup generators are advised for critical facilities. Estimated duration: 2 hours."
            ),
            Alert(
                "North York – Gas Line Inspection",
                "Safety inspection of underground gas pipelines will occur Wednesday morning. Technicians will be examining pressure valves and connections. Residents in North York may notice service interruptions or restricted usage during the inspection. Community centers and residential properties are included in the affected zone. Estimated duration: 8 AM – 12 PM."
            ),
            Alert(
                "Downtown Toronto – Road Closure",
                "Main Street will be closed this weekend for resurfacing and drainage improvements. Traffic will be diverted to nearby routes, and delays are expected. Commuters, delivery services, and local businesses along Main Street will be affected. Emergency vehicles will have designated alternate access routes. Estimated duration: Saturday morning through Sunday evening."
            ),
            Alert(
                "East York – Recycling Pickup Delay",
                "Recycling collection scheduled for Thursday will be delayed due to staffing shortages. Trucks will begin pickup later in the afternoon, and residents are advised to leave bins curbside until collected. Affected households are primarily in East York residential neighborhoods. Estimated delay: 4–6 hours."
            ),
            Alert(
                "Scarborough – Transit Service Disruption",
                "Track maintenance on Line 3 will cause shuttle buses to replace trains between Kennedy and McCowan stations. Commuters should expect longer travel times and plan accordingly. Affected: daily commuters and students. Estimated duration: Friday, all day."
            ),
            Alert(
                "Downtown Toronto – Water Main Break",
                "A water main break near Queen Street has caused flooding and service interruptions. Crews are working to repair the line and restore service. Businesses and residents in the downtown core are affected. Estimated duration: 6–8 hours."
            ),
            Alert(
                "Etobicoke – Garbage Collection Change",
                "Due to roadwork, garbage collection routes in Etobicoke will be adjusted. Trucks will collect waste earlier than usual, starting at 6 AM. Residents should place bins curbside the night before. Affected: residential neighborhoods west of Highway 427. Estimated duration: one‑day change."
            ),
            Alert(
                "North York – Internet Service Outage",
                "Fiber line damage has caused internet outages in parts of North York. Technicians are repairing the line, but connectivity may be intermittent until resolved. Affected: households and small businesses relying on high‑speed internet. Estimated duration: 12 hours."
            )
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
