package com.example.mobile_project.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mobile_project.models.Task

class AppDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "TaskManager.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create Users table
        db.execSQL("""
            CREATE TABLE Users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                email TEXT UNIQUE,
                password TEXT
            )
        """.trimIndent())

        // Create Tasks table
        db.execSQL("""
            CREATE TABLE Tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT,
                isCompleted INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Users")
        db.execSQL("DROP TABLE IF EXISTS Tasks")
        onCreate(db)
    }

    // ------------------ User Methods ------------------

    fun insertUser(name: String, email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("email", email)
            put("password", password)
        }
        return db.insert("Users", null, values) > 0
    }

    fun validateUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Users WHERE email = ? AND password = ?",
            arrayOf(email, password)
        )
        val isValid = cursor.count > 0
        cursor.close()
        return isValid
    }

    // ------------------ Task Methods ------------------

    fun insertTask(title: String, description: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
            put("isCompleted", 0)
        }
        return db.insert("Tasks", null, values) > 0
    }

    fun getAllTasks(): List<Task> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Tasks", null)
        val tasks = mutableListOf<Task>()
        while (cursor.moveToNext()) {
            tasks.add(
                Task(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow("isCompleted")) == 1
                )
            )
        }
        cursor.close()
        return tasks
    }

    fun updateTaskStatus(id: Int, isCompleted: Boolean): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("isCompleted", if (isCompleted) 1 else 0)
        }
        return db.update("Tasks", values, "id=?", arrayOf(id.toString())) > 0
    }

    fun deleteTask(id: Int): Boolean {
        val db = writableDatabase
        return db.delete("Tasks", "id=?", arrayOf(id.toString())) > 0
    }
}
