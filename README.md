# 📝 Task Manager App (Android, XML + SQLite)

This is a multi-screen Android application built for managing personal tasks. It supports user authentication, task creation, viewing, completion toggling, and deletion — all backed by a local SQLite database. The app is designed using XML layouts and follows modular architecture principles for clarity and maintainability.

---

## 🚀 Features

- 🔐 **User Authentication**
  - Signup and login screens with SQLite-backed credential validation

- 📋 **Task Management**
  - Add new tasks with title and description
  - View all tasks in a ListView
  - Mark tasks as completed
  - Delete tasks instantly

- 🗺️ **Map Integration**
  - Launches Google Maps to view Ontario Tech University

- 📷 **Camera Launch**
  - Opens device camera using intent

- 🔙 **Back Button Support**
  - ActionBar back navigation enabled on all screens

---

## 🛠️ Technologies Used

- **Language:** Kotlin
- **UI:** XML Layouts, ListView, Custom Adapter
- **Database:** SQLite via `AppDatabaseHelper`
- **Architecture:** Modular activities and helper classes
- **Navigation:** Explicit intents to switch between screens
- **Compatibility:** Android 12+ (with `android:exported` compliance)

---

## 📂 Project Structure

