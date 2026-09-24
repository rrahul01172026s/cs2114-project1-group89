# CS 2114: Project 1 — Beast Battles
**Group 89**  
**Term:** Fall 2026  
**Course:** CS 2114: Software Design & Data Structures (Virginia Tech)

---

## 👥 The Team & Contributions
* **Tien Vu** – Controllers & Architecture (`Game.java`, `InputHandler.java`, `BeastBattles.java`, session flow, starter selection logic).
* **Kyle Vasallo** – Battle Controller & Core Combat (`Battle.java`, turn-based combat loop, stamina regeneration & rest logic).
* **Karthik Mittadhoddi** – Models & Mechanics (`Attack.java`, `Beast.java`, `BeastType.java`, `DamageCalculator.java`).
* **Rahul** – Game Content & NPC Data (`Opponent.java`, `GameData.java`, `Display.java`, dialogue, starter/trainer balancing).

---

## 🎮 What is Beast Battles?
*Beast Battles* is a retro turn-based CLI battle game inspired by classic Pokémon battles. Built in pure Java, the player selects one of four elemental starter beasts and battles through a gauntlet of three increasingly difficult NPC trainers in a fixed order. 

To win the campaign, you must defeat all three trainers without fainting. If your beast falls in battle, your run ends, but you're safely sent back to the starter selection screen with all beasts restored to try again with a new strategy!

### 🌟 Key Features & Mechanics
1. **Four Starter Beasts:**
   * **Emberpup** (*FIRE*) – Fast, fiery, and hits hard.
   * **Sproutle** (*GRASS*) – Sturdy nature beast with solid endurance.
   * **Tidalot** (*WATER*) – Balanced aquatic fighter.
   * **Brawlet** (*NORMAL*) – Reliable brawler with no type weaknesses.
2. **Type Matchups:**
   * Water douses Fire ($2.0\times$ damage).
   * Fire burns Grass ($2.0\times$ damage).
   * Grass absorbs Water ($2.0\times$ damage).
   * Weak matchups deal half damage ($0.5\times$). Normal types deal and receive standard $1.0\times$ damage across the board.
3. **Stamina & Rest System (No Move Spamming!):**
   * Unlike basic games where you can just spam your strongest move, every attack costs **Stamina Points (SP)**.
   * Beasts passively regain $+3$ SP at the end of every turn.
   * If you're running low on stamina, you can spend your turn to **Rest**, recovering a massive $+12$ SP.
4. **NPC Opponent AI:**
   * Trainers (*Rookie Riley*, *Captain Marlow*, and *Champion Vex*) evaluate their stamina pool dynamically. If they can't afford any attack, they rest; otherwise, they select randomly among affordable moves.
5. **Campaign Progression:**
   * Your beast automatically receives a full heal (`restoreAll()`) after each victory so each fight is a fresh tactical puzzle.

---

## 🏛️ System Architecture (3-Tier Design)
We separated the codebase cleanly into Model-View-Controller layers so that no single class does everything:

```
cs2114-project1-group89/
│
├── 🧠 Models (Data & State, zero terminal interaction)
│   ├── Attack.java            # Immutable move definitions (power, type, cost)
│   ├── Beast.java             # Creature stats (HP, SP, moveset) with strict encapsulation
│   ├── BeastType.java         # FIRE, WATER, GRASS, NORMAL enum
│   ├── Opponent.java          # NPC trainers, dialogue (intro/win/loss), move AI
│   └── GameData.java          # Factory creating fresh starters & opponents per run
│
├── 🖥️ Terminal I/O / View (All screen rendering & keyboard parsing)
│   ├── Display.java           # The ONLY class that calls System.out
│   └── InputHandler.java      # Robust scanner parsing; prevents crashes on bad inputs
│
├── 🕹️ Controllers (Rules & State Transitions)
│   ├── DamageCalculator.java  # Pure calculation of type advantages and base powers
│   ├── Battle.java            # One individual fight turn-by-turn until a beast faints
│   ├── Game.java              # High-level session loop (run lifecycle, campaign, restarts)
│   └── BattleResult.java      # PLAYER_WON / PLAYER_LOST enum
│
└── 🚀 Entry Point
    └── BeastBattles.java      # main() wires dependencies together and launches game.run()
```

---

## 🛡️ Input Validation & Crash Prevention
We made sure that **nothing the player types into the terminal can crash the game**:
* We used `scanner.nextLine().trim()` coupled with `Integer.parseInt()` to avoid classic Scanner newline bugs that occur with `nextInt()`.
* Out-of-bounds numbers, text strings (`"fire"`, `"abc"`), and blank Enter presses reprompt gracefully with clear error messages.
* Trying to use a move without enough stamina prompts the player to pick another option without forfeiting their turn.
* Graceful exit: Pressing `Ctrl+D` (EOF) triggers a clean exit message (`"Goodbye!"`) instead of spilling an unhandled `NoSuchElementException` stack trace.

---

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

---

## 🧪 Testing & Code Coverage
We wrote extensive JUnit test suites (using Virginia Tech's `student.TestCase` framework) targeting 100% method and branch coverage:
* `AttackTest.java` – Validates immutability, getters, formatting, and constructor exceptions.
* `BeastTest.java` – Tests damage boundaries, health/stamina clamping, and fainting status.
* `OpponentTest.java` – Verifies move decision logic, rest triggering when out of stamina, and dialogue retrieval.
* `BattleTest.java` – Simulates turns with seeded `Random(42)` and scripted inputs.

To run all unit tests:
```bash
# In Eclipse:
Right-click project -> Run As -> JUnit Test
```

---

## 📝 Undergrad Retrospective & Lessons Learned
* **Merge Conflicts are real:** Working on git with four people taught us why agreeing on method signatures in Section 4 of our design doc *before* coding was an absolute lifesaver.
* **Separation of Concerns:** Resisting the urge to print from `Game` or `Battle` felt weird at first, but routing everything through `Display` made testing with `ByteArrayOutputStream` ten times easier.
* **Game Balancing:** We quickly learned during playtesting that without the stamina rest cost adjustments, players could just spam 10-power moves every single turn. Adding $+3$ stamina regen and $+12$ rest created actual decision-making!
