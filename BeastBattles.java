import java.util.Random;
import java.util.Scanner;

/**
 * Entry point for the Beast Battles game.
 *
 * @author Tien Vu
 * @author Antigravity
 * @version Sep 22, 2026
 */
public class BeastBattles {

    /**
     * Creates the real objects and calls game.run().
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {
        Display display = new Display(System.out);
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner, display);
        GameData gameData = new GameData();
        DamageCalculator damageCalculator = new DamageCalculator();
        Random random = new Random();

        Game game = new Game(gameData, inputHandler, display, damageCalculator, random);
        game.run();
    }
}

