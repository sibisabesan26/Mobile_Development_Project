package com.example.mobile_project.models

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean
)

