import java.io.PrintStream;
import java.util.List;

/**
 * All terminal output. No other class in the program prints, so changing
 * how the game looks means changing only this file.
 */
public class Display {
    private final PrintStream out;

    /**
     * @param out where to print; System.out in the real game
     * @throws IllegalArgumentException if out is null
     */
    public Display(PrintStream out) {
        if (out == null) {
            throw new IllegalArgumentException("Output stream cannot be null");
        }
        this.out = out;
    }

    /** Prints the game's title banner. */
    public void showTitle() {
        out.println("=================================");
        out.println("         BEAST  BATTLES");
        out.println("=================================");
    }

    /**
     * Prints each starter numbered 1 to 4 with its type, health, and moves.
     *
     * @param starters the Beasts to choose from
     * @throws IllegalArgumentException if the list is null or empty
     */
    public void showStarterMenu(List<Beast> starters) {
        if (starters == null || starters.isEmpty()) {
            throw new IllegalArgumentException("Starter list cannot be null or empty");
        }
        out.println("\nChoose your starter Beast:");
        for (int i = 0; i < starters.size(); i++) {
            Beast b = starters.get(i);
            out.println("  " + (i + 1) + ". " + b.getName() + " [" + b.getType()
                + "]  HP " + b.getMaxHealth());
            for (int j = 0; j < b.getAttackCount(); j++) {
                out.println("       - " + b.getAttack(j));
            }
        }
    }

    /**
     * Prints which battle this is and which trainer and Beast appear.
     *
     * @param opponent     the trainer being faced
     * @param battleNumber which battle this is, starting at 1
     * @param totalBattles how many battles there are in total
     * @throws IllegalArgumentException if opponent is null
     */
    public void showOpponentAppears(Opponent opponent, int battleNumber, int totalBattles) {
        if (opponent == null) {
            throw new IllegalArgumentException("Opponent cannot be null");
        }
        Beast enemy = opponent.getBeast();
        out.println("\n--- Battle " + battleNumber + " of " + totalBattles + " ---");
        out.println(opponent.getName() + " sends out " + enemy.getName()
            + " [" + enemy.getType() + "]!");
    }

    /**
     * Prints both Beasts' health and stamina at the start of a turn.
     *
     * @param player the player's Beast
     * @param enemy  the opposing Beast
     * @throws IllegalArgumentException if either Beast is null
     */
    public void showStatus(Beast player, Beast enemy) {
        requireBeast(player);
        requireBeast(enemy);
        out.println();
        out.println("  You:   " + player);
        out.println("  Enemy: " + enemy);
    }

    /**
     * Prints the four moves numbered 1 to 4, marking any the Beast cannot
     * currently afford, plus option 5 to rest.
     *
     * @param beast         the Beast whose moves to show
     * @param restRecovery  how much stamina resting gives back
     * @throws IllegalArgumentException if beast is null
     */
    public void showMoveMenu(Beast beast, int restRecovery) {
        requireBeast(beast);
        out.println("Moves:");
        for (int i = 0; i < beast.getAttackCount(); i++) {
            Attack a = beast.getAttack(i);
            String note = beast.canUse(a) ? "" : "   (not enough stamina)";
            out.println("  " + (i + 1) + ". " + a + note);
        }
        out.println("  " + (beast.getAttackCount() + 1) + ". Rest (+" + restRecovery + " stamina)");
    }

    /**
     * @param beast  the Beast that rested
     * @param amount stamina recovered
     * @throws IllegalArgumentException if beast is null
     */
    public void showRest(Beast beast, int amount) {
        requireBeast(beast);
        out.println(beast.getName() + " rests and recovers " + amount + " stamina.");
    }

    /**
     * Prints a prompt without a line break, so the player types on the
     * same line.
     *
     * @param prompt the question to ask
     */
    public void showPrompt(String prompt) {
        out.print(prompt + ": ");
    }

    /**
     * Prints who attacked, with what, and for how much damage.
     *
     * @param attacker the Beast that attacked
     * @param attack   the move used
     * @param damage   damage dealt
     * @throws IllegalArgumentException if attacker or attack is null
     */
    public void showAttack(Beast attacker, Attack attack, int damage) {
        requireBeast(attacker);
        if (attack == null) {
            throw new IllegalArgumentException("Attack cannot be null");
        }
        out.println(attacker.getName() + " used " + attack.getName()
            + "! It did " + damage + " damage.");
    }

    /**
     * Prints a type-matchup message, or nothing if the move was neutral.
     *
     * @param multiplier the effectiveness from DamageCalculator
     */
    public void showEffectiveness(double multiplier) {
        if (multiplier > 1.0) {
            out.println("  It's super effective!");
        }
        else if (multiplier < 1.0) {
            out.println("  It's not very effective...");
        }
    }

    /**
     * @param enemy the Beast that fainted
     * @throws IllegalArgumentException if enemy is null
     */
    public void showBattleWon(Beast enemy) {
        requireBeast(enemy);
        out.println(enemy.getName() + " fainted! You won the battle!");
    }

    /**
     * @param player the player's Beast that fainted
     * @throws IllegalArgumentException if player is null
     */
    public void showBattleLost(Beast player) {
        requireBeast(player);
        out.println(player.getName() + " fainted! You lost the battle.");
    }

    /** Prints the final message after every opponent is beaten. */
    public void showVictory() {
        out.println("\n*** You defeated all three enemies. You are the Beast Battles champion! ***");
    }

    /** Prints the game-over message before starter selection restarts. */
    public void showDefeat() {
        out.println("\nGame over. Returning to starter selection...");
    }

    /**
     * @param message why the input was rejected
     */
    public void showError(String message) {
        out.println("  Invalid input: " + message);
    }

    /**
     * Prints any general line, including trainer dialogue.
     *
     * @param message the text to print
     */
    public void showMessage(String message) {
        out.println(message);
    }

    private void requireBeast(Beast b) {
        if (b == null) {
            throw new IllegalArgumentException("Beast cannot be null");
        }
    }
}