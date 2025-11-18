package com.example.project

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var imgAvatar: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView
    private lateinit var btnEditProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // ✅ Bind views
        imgAvatar = findViewById(R.id.imgAvatar)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        btnEditProfile = findViewById(R.id.btnEditProfile)

        // ✅ Demo data (replace with real user data later)
        txtName.text = "John Doe"
        txtEmail.text = "johndoe@example.com"

        // ✅ Handle edit button
        btnEditProfile.setOnClickListener {
            // For now, just show a message or navigate to an EditProfileActivity
            // Example: Toast.makeText(this, "Edit profile clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
