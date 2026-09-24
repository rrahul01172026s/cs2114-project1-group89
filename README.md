# CS 2114: Project 1 — Beast Battles
**Group 89**  
**Term:** Fall 2026  
**Course:** CS 2114: Software Design & Data Structures (Virginia Tech)

---

## 🎮 What is Beast Battles?
*Beast Battles* is a retro turn-based CLI battle game inspired by classic Pokémon battles. Built in pure Java, the player selects one of four elemental starter beasts and battles through a gauntlet of three increasingly difficult NPC trainers in a fixed order. 

To win the campaign, you must defeat all three trainers without fainting. If your beast falls in battle, your run ends, but you're safely sent back to the starter selection screen with all beasts restored to try again with a new strategy!



## ⚙️ How to Compile & Play

### Option 1: Terminal / Command Prompt
Make sure you have Java JDK 17+ installed.

1. Navigate to the project directory:
   ```bash
   cd cs2114-project1-group89
   ```
2. Compile all Java source files:
   ```bash
   javac *.java
   ```
3. Run the game:
   ```bash
   java BeastBattles
   ```

### Option 2: Eclipse / VS Code
* Import the repository as an existing Java Project.
* Ensure JDK 17 (or class-standard Java) is configured on the build path.
* Right click `BeastBattles.java` -> **Run As** -> **Java Application**.