package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnGarbage: MaterialButton
    private lateinit var btnUtility: MaterialButton
    private lateinit var btnAlerts: MaterialButton
    private lateinit var btnFeedback: MaterialButton
    private lateinit var btnFaq: MaterialButton
    private lateinit var btnProfile: ImageButton
    private lateinit var btnFaqIcon: ImageButton
    private lateinit var newsSlider: RecyclerView
    private lateinit var fadedLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ✅ Bind buttons
        btnGarbage = findViewById(R.id.btnGarbageCollection)
        btnUtility = findViewById(R.id.btnUtilityTracking)
        btnAlerts = findViewById(R.id.btnLocalServiceAlerts)
        btnFeedback = findViewById(R.id.btnFeedback)
        btnFaq = findViewById(R.id.btnFaq)
        btnProfile = findViewById(R.id.btnProfile)
        btnFaqIcon = findViewById(R.id.btnFaqIcon)
        newsSlider = findViewById(R.id.newsSlider)
        fadedLogo = findViewById(R.id.imgFadedLogo)

        // ✅ Button navigation
        btnGarbage.setOnClickListener {
            startActivity(Intent(this, GarbageCollectionActivity::class.java))
        }

        btnUtility.setOnClickListener {
            startActivity(Intent(this, UtilityTrackingActivity::class.java))
        }

        btnAlerts.setOnClickListener {
            startActivity(Intent(this, LocalServiceAlertsActivity::class.java))
        }

        btnFeedback.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }

        btnFaq.setOnClickListener {
            startActivity(Intent(this, FaqActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnFaqIcon.setOnClickListener {
            startActivity(Intent(this, FaqActivity::class.java))
        }

        // ✅ News slider setup
        newsSlider.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val newsItems = listOf(
            NewsItem(
                "City expands recycling pickup in East York",
                "Households will now have weekly recycling collection starting next month, reducing landfill waste."
            ),
            NewsItem(
                "Water rates rising in January",
                "Toronto households will see a 3% increase on monthly water bills to fund infrastructure upgrades."
            ),
            NewsItem(
                "Child tax payments boosted",
                "Families to receive higher monthly benefits beginning Thursday to offset rising living costs."
            ),
            NewsItem(
                "Enbridge expands oil flow to U.S.",
                "A US$1.4B pipeline project increases Canadian crude exports, with long‑term impacts on energy prices."
            ),
            NewsItem(
                "Hydro One launches smart meter program",
                "New meters will help households track electricity use in real time and cut monthly bills."
            ),
            NewsItem(
                "Affordable housing units approved",
                "Toronto council greenlights 2,000 new units, aiming to ease pressure on families facing rent hikes."
            ),
            NewsItem(
                "Internet providers pledge faster speeds",
                "Major carriers announce upgrades to fiber networks across GTA, improving service for households."
            ),
            NewsItem(
                "Transit expansion to suburban areas",
                "New bus rapid transit lines will connect Scarborough and Etobicoke, reducing commute times for families."
            )
        )


        newsSlider.adapter = NewsAdapter(newsItems)

        // ✅ Faded logo already handled in XML — no code needed unless dynamic
    }
}
