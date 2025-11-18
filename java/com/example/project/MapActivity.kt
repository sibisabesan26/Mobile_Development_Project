package com.example.project

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // ✅ Back button overlay
        val backButton = findViewById<Button>(R.id.btnBack)
        backButton?.setOnClickListener {
            finish()
        }

        // ✅ Load map fragment
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // ✅ Extract weekday from formatted string like "Monday, Nov 17"
        val selectedDay = intent.getStringExtra("day")?.split(",")?.first()?.trim() ?: ""

        // ✅ Define garbage zones
        val westArea = listOf(
            LatLng(43.633090, -79.462985),
            LatLng(43.533663, -79.600565),
            LatLng(43.651884, -79.760877),
            LatLng(43.777837, -79.549206),
            LatLng(43.633090, -79.462985) // close polygon
        )

        val centralArea = listOf(
            LatLng(43.777837, -79.549206),
            LatLng(43.856334, -79.249913),
            LatLng(43.736238, -79.172136),
            LatLng(43.633090, -79.462985),
            LatLng(43.777837, -79.549206)
        )

        val eastArea = listOf(
            LatLng(43.929411, -79.070730),
            LatLng(43.856334, -79.249913),
            LatLng(43.736238, -79.172136),
            LatLng(43.834678, -78.959972),
            LatLng(43.929411, -79.070730)
        )

        // ✅ Draw zone based on weekday
        when (selectedDay) {
            "Monday", "Thursday" -> {
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(westArea)
                        .fillColor(Color.argb(85, 33, 150, 243)) // Blue
                        .strokeColor(Color.rgb(33, 150, 243))
                        .strokeWidth(4f)
                )
            }
            "Tuesday", "Friday" -> {
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(centralArea)
                        .fillColor(Color.argb(85, 76, 175, 80)) // Green
                        .strokeColor(Color.rgb(76, 175, 80))
                        .strokeWidth(4f)
                )
            }
            "Wednesday" -> {
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(eastArea)
                        .fillColor(Color.argb(85, 255, 152, 0)) // Orange
                        .strokeColor(Color.rgb(255, 152, 0))
                        .strokeWidth(4f)
                )
            }
            // Saturday/Sunday → no polygon
        }

        // ✅ Center camera on Toronto
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(43.72, -79.35), 10f))
    }
}
