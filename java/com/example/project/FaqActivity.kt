package com.example.project

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class FaqActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FaqAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "FAQ"

        recyclerView = findViewById(R.id.recyclerFaq)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val faqItems = listOf(
            FaqItem("How do I check garbage schedules?", "android.resource://${packageName}/${R.raw.vid5}"),
            FaqItem("How do I see local alerts?", "android.resource://${packageName}/${R.raw.vid3}"),
            FaqItem("How do I give feedback?", "android.resource://${packageName}/${R.raw.vid3}")
        )

        adapter = FaqAdapter(faqItems)
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
