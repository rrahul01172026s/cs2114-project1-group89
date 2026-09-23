import java.util.Random;

/**
 * Handles the turn-based combat
 * 
 * @author kvasallo
 * @version Sep 17, 2026
 */
public class Battle {
    // ~ Fields ................................................................
    private static final int STAMINA_REGEN = 1;
    private static final int REST_STAMINA_RECOVERED = 1;

    // String types are placeholders for when the actual classes are built
    private Beast playerBeast;
    private Opponent opponent;
    private Beast opponentBeast;
    private InputHandler inputHandler;
    private Display display;
    private DamageCalculator calculator;
    private Random random;
    private int turnNumber;

    private boolean playerFirst;

    // ~ Constructors ..........................................................
    /**
     * Create a new Battle object.
     * 
     * @param playerBeast
     *            The player's Beast
     * @param opponent
     *            The current opponent being faced
     * @param inputHandler
     *            The inputHandler
     * @param display
     *            The Display
     * @param calculator
     *            The calculator
     * @param random
     *            Random number generator
     * @throws IllegalArgumentException
     *             When an argument is null
     */
    public Battle(
        Beast playerBeast,
        Opponent opponent,
        InputHandler inputHandler,
        Display display,
        DamageCalculator calculator,
        Random random) {

        if (playerBeast == null || opponent == null || inputHandler == null
            || display == null || calculator == null || random == null) {
            throw new IllegalArgumentException();
        }

        this.playerBeast = playerBeast;
        this.opponent = opponent;
        this.opponentBeast = opponent.getBeast();
        this.inputHandler = inputHandler;
        this.display = display;
        this.calculator = calculator;
        this.random = random;
        turnNumber = 0;
        determineTurnOrder();

    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Gets the number of turns that have been played thus far
     * 
     * @return numberOfTurnsElapsed number of turns that have been played
     */
    public int getTurnNumber() {
        return turnNumber;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return true if enemy beast fainted
     */
    public BattleResult getResult() {
        if (opponentBeast.isFainted()) {
            return BattleResult.PLAYER_WON;
        }
        if (playerBeast.isFainted()) {
            return BattleResult.PLAYER_LOST;
        }
        throw new IllegalStateException("Battle is not over yet");
    }


    // ----------------------------------------------------------
    /**
     * Adds dialogue and continually calls playTurn until a victor is determined
     * 
     * @return battle result PLAYER_WON or PLAYER_LOST either player's beast
     *         fainted or the
     *         opponent beast fainted
     */
    public BattleResult run() {
        playerBeast.restoreAll();
        opponentBeast.restoreAll();

        display.showMessage(opponent.getIntroLine());
        while (!isOver()) {
            playTurn();
        }
        BattleResult result = getResult();
        if (result == BattleResult.PLAYER_WON) {
            display.showBattleWon(opponentBeast);
            display.showMessage(opponent.getLoseLine());
        }
        else {
            display.showBattleLost(playerBeast);
            display.showMessage(opponent.getWinLine());
        }

        return result;
    }


    private boolean isOver() {
        return playerBeast.isFainted() || opponentBeast.isFainted();
    }


    /**
     * Runs through a single turn of a participant
     * @precondition isOver cannot be true
     */
    private void playTurn() {
        if (playerFirst) {
            display.showStatus(playerBeast, opponentBeast);
            Attack chosenAttack = choosePlayerAttack();
            if (chosenAttack == null) {
                rest(playerBeast);
            }
            else {
                useAttack(playerBeast, opponentBeast, chosenAttack);
            }
        }

        if (!opponentBeast.isFainted()) {
            if (opponent.shouldRest()) {
                rest(opponentBeast);
            }
            else {
                useAttack(opponentBeast, playerBeast, opponent.chooseAttack(
                    random));
            }
        }

        if (!playerBeast.isFainted()) {
            playerBeast.recoverStamina(STAMINA_REGEN);
        }

        if (!opponentBeast.isFainted()) {
            opponentBeast.recoverStamina(STAMINA_REGEN);
        }

    }


    private Attack choosePlayerAttack() {
        int restOption = playerBeast.getAttackCount() + 1;
        while (true) {
            int choice = inputHandler.readChoice("Choose move", 1, restOption);
            if (choice == restOption) {
                return null;
            }

            Attack attackChosen = playerBeast.getAttack(choice - 1);
            if (playerBeast.canUse(attackChosen)) {
                return attackChosen;
            }
            display.showError("pick a different move");
        }
    }


    private void rest(Beast beast) {
        beast.recoverStamina(REST_STAMINA_RECOVERED);
        display.showRest(beast, REST_STAMINA_RECOVERED);
    }


    private void determineTurnOrder() {
        if (playerBeast.getSpeed() >= opponentBeast.getSpeed()) {
            playerFirst = true;
        }
        else {
            playerFirst = false;
        }
    }


    private void useAttack(Beast attacker, Beast defender, Attack attack) {
        attacker.useStamina(attack.getStaminaCost());
        int damage = calculator.calculateDamage(attack, defender);
        defender.takeDamage(damage);
        display.showAttack(attacker, attack, damage);
        display.showEffectiveness(calculator.getEffectiveness(attack.getType(),
            defender.getType()));
    }

}
