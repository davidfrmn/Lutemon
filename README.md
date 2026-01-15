# Lutemon - Android Battle & Training Game

Lutemon is an Android application where users can create, train, and battle unique creatures called Lutemons. The app features a persistent storage system using JSON, a variety of Lutemon types with unique stats, and a turn-based battle system.

## 🚀 Features

* **Lutemon Creation**: Choose from five distinct species—**Orange, Green, Pink, Black, and White**. Each species has unique base attributes.
* **Training Grounds**: Enhance your Lutemon's strength through training. Training increases Experience (EXP), which dynamically scales Max HP, Attack, and Defense.
* **Tactical Combat**: A turn-based battle system where you can choose to:
    * **Attack**: Deal damage to the enemy.
    * **Defend**: Mitigate incoming damage and gain a temporary defense boost for the battle.
    * **Dodge**: Built-in mechanics where high-speed Lutemons have a chance to evade attacks entirely.
* **Statistics Tracking**: Monitor your overall progress, including the total number of battles fought, training sessions completed, and your win-loss record.
* **Data Persistence**: All Lutemons and progress are saved locally to a JSON file (`lutemons.json`), ensuring your team is preserved across app restarts.

---

## 🛠️ Architecture & Tech Stack

The project is built using **Java** and follows standard Android development patterns:

### Core Logic
* **Storage (Singleton Pattern)**: Centralized data management that persists throughout the application lifecycle.
* **Lutemon Hierarchy**: Uses Object-Oriented principles where specific Lutemon types inherit from an abstract base class.
* **FileHandler**: Manages data I/O, converting Java objects to JSON format and vice versa.

### UI & Interaction
* **RecyclerView**: Used in `SelectLutemonActivity` and `StatisticsActivity` for efficient list rendering.
* **Edge-to-Edge**: Implements modern Android UI layouts that utilize the full screen.
* **Activity-Based Navigation**: Uses Intents to move between creation, selection, training, and combat modules.

---

## 🎮 How to Play

1.  **Create Your Team**: Navigate to the "Create Lutemon" menu. Name your creature and select its color/type.
2.  **Select Active Lutemon**: You must select an "Active" Lutemon from your list before you can train or battle.
3.  **Train**: Visit the Training area to boost your stats. High EXP is key to surviving tougher battles.
4.  **Battle**: Enter the Arena. During your turn, choose to attack or defend. Winning a battle awards a win point and additional EXP. If your Lutemon's health reaches zero, the match ends!


---

## ⚙️ Requirements

* **Android Studio**.
* **Minimum SDK**: API 24 (Android 7.0).

---

This project was developed as a programming exercise for Android development.
