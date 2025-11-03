# 📝 Task Manager App (Android, XML + SQLite)

Our project, The Smart Neighbourhood App, is designed to simplify household management by integrating garbage collection schedules, utility tracking, and local service alerts into a single mobile platform. The motivation for creating this app stems from the need for residents to have a centralized and reliable system to manage and oversee everyday responsibilities that are often scattered across multiple websites or sources. The application aims to address the nature of current information sources, it aims to improve convenience, awareness, and communication within residential communities. The app is built using XML layouts and SQLite for local data persistence, with additional features like map and camera integration

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

