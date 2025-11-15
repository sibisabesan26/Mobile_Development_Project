package com.example.project

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.project.databinding.ActivityFeedbackBinding
import java.io.File

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    private val PICK_IMAGE = 1001
    private val PICK_VIDEO = 1002
    private val CAPTURE_IMAGE = 1003

    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Toolbar back button
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // ✅ Submit button logic
        binding.btnSubmit.setOnClickListener {
            val name = binding.inputName.text.toString()
            val email = binding.inputEmail.text.toString()
            val comments = binding.inputComments.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && comments.isNotEmpty()) {
                Toast.makeText(this, "Feedback submitted. Thank you!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // ✅ Attach photo from gallery
        binding.btnAttachPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(Intent.createChooser(intent, "Select Photo"), PICK_IMAGE)
        }

        // ✅ Capture photo with camera
        binding.btnAttachCamera.setOnClickListener {
            val photoFile = File.createTempFile("feedback_photo", ".jpg", cacheDir)
            photoUri = FileProvider.getUriForFile(this, "${packageName}.provider", photoFile)

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            }
            startActivityForResult(intent, CAPTURE_IMAGE)
        }

        // ✅ Attach video
        binding.btnAttachVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "video/*"
            }
            startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE -> {
                    val uri: Uri? = data?.data
                    binding.previewImage.setImageURI(uri)
                    binding.previewImage.visibility = android.view.View.VISIBLE
                    binding.previewVideo.visibility = android.view.View.GONE
                }
                CAPTURE_IMAGE -> {
                    binding.previewImage.setImageURI(photoUri)
                    binding.previewImage.visibility = android.view.View.VISIBLE
                    binding.previewVideo.visibility = android.view.View.GONE
                }
                PICK_VIDEO -> {
                    val uri: Uri? = data?.data
                    binding.previewVideo.setVideoURI(uri)
                    binding.previewVideo.setMediaController(MediaController(this))
                    binding.previewVideo.start()
                    binding.previewVideo.visibility = android.view.View.VISIBLE
                    binding.previewImage.visibility = android.view.View.GONE
                }
            }
        }
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
