# ⚔️ Mystic Realms
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![IDE](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Status](https://img.shields.io/badge/Status-In_Progress-yellow?style=for-the-badge)

> A console-based turn-based RPG game built
> with pure Kotlin. Save the kingdom of
> Eldoria from the forces of darkness!

---

## 👨‍💻 About

Hi! I'm Krishna, a final year student
passionate about Android development.
This project is part of my Kotlin learning
journey before moving to Android Studio
and Jetpack Compose.

Mystic Realms started as a simple RPG and
grew into a multi-file project with real
game systems — built completely from scratch
while learning Kotlin concepts step by step.

---
## 📸 Screenshots

![Story](V2/Assets/story.png)
![Intro](V2/Assets/Intro.png)
![Location](V2/Assets/ExploreLocation.png)
![Inventory](V2/Assets/Inventory.png)
![CurrentStats](V2/Assets/CurrentStats.png)
![attacks](V2/Assets/AttackTypes.png)
![Ending](V2/Assets/GameOver.png)

## 🚀 How to Run

### IntelliJ IDEA
1. Download files from V1 or V2 folder
2. New Project → Kotlin in IntelliJ IDEA
3. Paste files in src folder
4. Run Main.kt ▶️

### Kotlin Playground (No Installation)
1. Go to play.kotlinlang.org
2. Paste Main.kt code
3. Click Run ▶️

---

## 📁 Versions

### V1 — Base Version
Single file RPG with basic combat system.
```
V1/
└── Main.kt
```
**Features:**
- 🗺️ 4 locations to explore
- 👹 4 different monsters
- ⚔️ Attack, Fireball, Lightning
- 🧪 Unlimited potion healing
- 🎭 Village Elder quest storyline

---

### V2 — Major Upgrade
Multi-file architecture with advanced systems.
```
V2/
├── Main.kt      ← Entry point
├── Game.kt      ← Game logic
└── UIHelper.kt  ← UI functions
```
**What's New:**
- ❤️ Visual HP bars with colors 🟢🟡🔴
- ⭐ XP progress bar
- ⚔️ 4 random monster attack types
- 🎒 Limited inventory system
- 📈 Level up with XP thresholds
- 🎁 Level up item rewards
- 🎨 Box border UI
- 🔄 Continue exploring + play again loop
- 🏆 Dramatic story endings

---

## 🆚 V1 vs V2

| Feature | V1 | V2 |
|---------|----|----|
| 📁 Structure | Single file | Multi-file |
| ❤️ HP Display | Text only | Visual bars |
| ⚔️ Monster Attack | Fixed | 4 Random types |
| 🧪 Potions | Unlimited | Limited |
| 🎒 Inventory | None | Full system |
| 📈 Level Up | Basic | XP + Rewards |
| 🎮 UI | Plain text | Box borders |
| 🔄 Play Again | Manual rerun | Auto loop |

---

## 🧠 Kotlin Concepts Used

| Concept | Used For |
|---------|----------|
| `data class` | Player, Monster, Item |
| `enum class` | Location, AttackType, ItemType |
| `when` expression | Combat, item types |
| Higher-order functions | playerAttack() lambda |
| `MutableList` | Inventory system |
| `forEachIndexed` | Show inventory |
| `.copy()` | Update player stats |
| `.coerceAtMost()` | HP heal limit |
| `.random()` | Monster attacks |
| Null safety `?.` `?:` | readLine() handling |

---

## 🔮 V3 — Planned
- 💾 File-based save system
- 🏪 Shop with gold coins
- 😊 Difficulty levels
- 💥 Limited special attacks
- 🤝 NPC characters

---

## 👨‍💻 Author

**Prince**
Final Year Student | Android Developer in Progress 🚀

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Prince-AppDev)
