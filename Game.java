import java.util.List;

import java.util.Random;
import java.util.NoSuchElementException;

/**
 * Need to instantiate the final variables listed down below in order for game
 * class to work.
 * Game data should have the main game run inside this class; with other
 * interconnecting classes such as the beasts, and etc.
 * 
 * @author tienvu
 * @version 9-17-26
 *
 */
public class Game {

    private final GameData data;
    private final InputHandler input;
    private final Display display;
    private final DamageCalculator calculator;
    private final Random random;

    /** This run's three starter choices. */
    private List<Beast> starters;

    /** Opponents not yet beaten, in fight order. */
    private List<Opponent> opponents;

    /** The player's chosen Beast; null until {@link #chooseStarter()} runs. */
    private Beast playerBeast;

    private int opponentsDefeated;

    /**
     * Stores the dependencies used for the whole session.
     *
     * @throws IllegalArgumentException
     *             if any argument is null
     */
    public Game(
        GameData data,
        InputHandler input,
        Display display,
        DamageCalculator calculator,
        Random random) {
        if (data == null || input == null || display == null
            || calculator == null || random == null) {
            throw new IllegalArgumentException(
                "Game dependencies must not be null.");
        }
        this.data = data;
        this.input = input;
        this.display = display;
        this.calculator = calculator;
        this.random = random;
    }


    /**
     * The main game loop. Builds a fresh run, lets the player pick a
     * starter, then fights the campaign; on a loss it loops back to a new
     * run, on a win it shows the victory screen and stops. If the input
     * stream ends entirely, catches {@link NoSuchElementException} and
     * exits with a goodbye message instead of a stack trace.
     */
    public void run() {
        try {
            boolean won = false;
            while (!won) {
                startNewRun();
                chooseStarter();
                won = playCampaign();
            }
            display.showVictory();
        }
        catch (NoSuchElementException e) {
            display.showMessage("Goodbye!");
        }
    }


    /**
     * Replaces the starters and opponents with fresh copies from
     * {@link GameData}, so a restarted run can never inherit damage or
     * defeated opponents from the last one.
     */
    public void startNewRun() {
        starters = data.createStarters();
        opponents = data.createOpponents();
        playerBeast = null;
        opponentsDefeated = 0;
    }


    /**
     * Shows the starter menu, reads the player's choice, and stores and
     * returns that Beast.
     *
     * @return the chosen starter Beast
     * @throws IllegalStateException if called before starting a new run
     */
    public Beast chooseStarter() {
        if (starters == null) {
            throw new IllegalStateException("Must start a new run first.");
        }
        display.showStarterMenu(starters);
        int choice = input.readChoice("Pick a starter", 1, starters.size());
        playerBeast = starters.get(choice - 1);
        return playerBeast;
    }


    /**
     * Fights opponents in order until all are beaten or the player loses.
     * On a win against an opponent, the player's Beast is fully healed before the
     * next fight. On a loss, the defeat screen is shown.
     *
     * @return true once every opponent has been beaten; false if the player lost a battle
     * @throws IllegalStateException if called before a starter is chosen
     */
    public boolean playCampaign() {
        if (playerBeast == null) {
            throw new IllegalStateException("Must choose a starter first.");
        }
        for (Opponent opponent : opponents) {
            Battle battle = new Battle(playerBeast, opponent, input, display,
                calculator, random);
            BattleResult result = battle.run();

            if (result == BattleResult.PLAYER_LOST) {
                display.showDefeat();
                return false;
            }

            opponentsDefeated++;
            playerBeast.restoreAll();
        }
        return true;
    }


    /**
     * @return the player's chosen beast
     */
    public Beast getPlayerBeast() {
        return playerBeast;
    }


    /**
     * @return the number of opponents not yet beaten, for tests
     */
    public int getOpponentsRemaining() {
        if (opponents == null) {
            return 0;
        }
        return opponents.size() - opponentsDefeated;
    }
}
