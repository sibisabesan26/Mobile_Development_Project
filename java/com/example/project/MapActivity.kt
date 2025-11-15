package com.example.project   // make sure this matches your app package

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

        // Back button overlay
        val backButton = findViewById<Button>(R.id.btnBack)
        backButton?.setOnClickListener {
            finish() // closes MapActivity and returns to previous screen
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val selectedDay = intent.getStringExtra("day") ?: ""

        // ✅ West Toronto / Etobicoke
        val westArea = listOf(
            LatLng(43.633090, -79.462985),
            LatLng(43.533663, -79.600565),
            LatLng(43.651884, -79.760877),
            LatLng(43.777837, -79.549206)
        )

        // ✅ Central Toronto / Downtown + Midtown
        val centralArea = listOf(
            LatLng(43.777837, -79.549206),
            LatLng(43.856334, -79.249913),
            LatLng(43.736238, -79.172136),
            LatLng(43.633090, -79.462985)
        )

        // ✅ East Toronto / Scarborough
        val eastArea = listOf(
            LatLng(43.929411, -79.070730),
            LatLng(43.856334, -79.249913),
            LatLng(43.736238, -79.172136),
            LatLng(43.834678, -78.959972)
        )

        // ✅ Show zones based on selected day
        when (selectedDay) {
            "Monday" -> {
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(westArea)
                        .fillColor(0x552196F3)
                        .strokeColor(0xFF2196F3.toInt())
                        .strokeWidth(4f)
                )
            }

            "Tuesday" -> {
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(centralArea)
                        .fillColor(0x554CAF50)
                        .strokeColor(0xFF4CAF50.toInt())
                        .strokeWidth(4f)
                )
            }

            "Wednesday" -> {
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(eastArea)
                        .fillColor(0x55FF9800)
                        .strokeColor(0xFFFF9800.toInt())
                        .strokeWidth(4f)
                )
            }

            "Thursday" -> {
                // Example: show west again
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(westArea)
                        .fillColor(0x552196F3)
                        .strokeColor(0xFF2196F3.toInt())
                        .strokeWidth(4f)
                )
            }

            "Friday" -> {
                // Example: show central again
                googleMap.addPolygon(
                    PolygonOptions()
                        .addAll(centralArea)
                        .fillColor(0x554CAF50)
                        .strokeColor(0xFF4CAF50.toInt())
                        .strokeWidth(4f)
                )
            }
            // Saturday/Sunday → no polygons
        }

        // ✅ Center camera on Toronto
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(43.72, -79.35), 10f))
    }
}
