package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // ✅ Wire up Toolbar as ActionBar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false) // No back button on Main screen

        // Welcome text
        binding.welcomeText.text = "Welcome!"

        // ✅ Garbage Collection button
        binding.btnGarbageSchedules.setOnClickListener {
            val intent = Intent(this, GarbageCollectionActivity::class.java)
            startActivity(intent)
        }

        // ✅ Utility Tracking button
        binding.btnUtilityTracking.setOnClickListener {
            val intent = Intent(this, UtilityTrackingActivity::class.java)
            startActivity(intent)
        }

        // ✅ Local Service Alerts button
        binding.btnLocalServiceAlerts.setOnClickListener {
            val intent = Intent(this, LocalServiceAlertsActivity::class.java)
            startActivity(intent)
        }

        // ✅ Give Feedback button
        binding.btnFeedback.setOnClickListener {
            val intent = Intent(this, FeedbackActivity::class.java)
            startActivity(intent)
        }
// ✅ FAQ button
        binding.btnFaq.setOnClickListener {
            val intent = Intent(this, FaqActivity::class.java)
            startActivity(intent)
        }


    }
}
