package com.example.project

data class FaqItem(
    val question: String,
    val videoUrl: String,
    var isExpanded: Boolean = false
)
