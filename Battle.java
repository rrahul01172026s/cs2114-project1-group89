import java.util.Random;

/**
 * Handles the turn-based combat
 * 
 * @author kvasallo
 * @author Tien Vu
 * @author Antigravity
 * @version Sep 22, 2026
 */
public class Battle {
    // ~ Fields ................................................................
    private static final int STAMINA_REGEN = 3;
    private static final int REST_RECOVERY = 12;

    private final Beast playerBeast;
    private final Opponent opponent;
    private final Beast enemyBeast;
    private final InputHandler input;
    private final Display display;
    private final DamageCalculator calculator;
    private final Random random;
    private int turnNumber;

    // ~ Constructors ..........................................................
    /**
     * Create a new Battle object.
     * 
     * @param playerBeast The player's Beast
     * @param opponent The current opponent being faced
     * @param input The inputHandler
     * @param display The Display
     * @param calculator The calculator
     * @param random The Random instance for opponent moves
     * @throws IllegalArgumentException When an argument is null
     */
    public Battle(
        Beast playerBeast,
        Opponent opponent,
        InputHandler input,
        Display display,
        DamageCalculator calculator,
        Random random) {

        if (playerBeast == null || opponent == null || input == null
            || display == null || calculator == null || random == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        this.playerBeast = playerBeast;
        this.opponent = opponent;
        this.enemyBeast = opponent.getBeast();
        this.input = input;
        this.display = display;
        this.calculator = calculator;
        this.random = random;
        this.turnNumber = 0;
    }

    // ----------------------------------------------------------
    /**
     * Gets the number of turns that have been played thus far
     * 
     * @return number of turns that have been played
     */
    public int getTurnNumber() {
        return turnNumber;
    }

    /**
     * Place a description of your method here.
     * 
     * @return true if enemy beast fainted
     */
    public boolean isOver() {
        return playerBeast.isFainted() || enemyBeast.isFainted();
    }
    
    public BattleResult getResult() {
        if (!isOver()) {
            throw new IllegalStateException("Battle is not over yet");
        }
        if (enemyBeast.isFainted()) {
            return BattleResult.PLAYER_WON;
        }
        return BattleResult.PLAYER_LOST;
    }

    // ----------------------------------------------------------
    /**
     * Adds dialogue and continually calls playTurn until a victor is determined
     * 
     * @return battle result PLAYER_WON or PLAYER_LOST
     */
    public BattleResult run() {
        display.showMessage(opponent.getIntroLine());
        while (!isOver()) {
            playTurn();
        }
        BattleResult result = getResult();
        if (result == BattleResult.PLAYER_WON) {
            display.showBattleWon(enemyBeast);
            display.showMessage(opponent.getLoseLine());
        }
        else {
            display.showBattleLost(playerBeast);
            display.showMessage(opponent.getWinLine());
        }

        return result;
    }

    /**
     * Runs through a single turn of a participant
     * @precondition isOver cannot be true
     */
    public void playTurn() {
        if (isOver()) {
            throw new IllegalStateException("Battle is over");
        }

        turnNumber++;
        display.showStatus(playerBeast, enemyBeast);
        display.showMoveMenu(playerBeast, REST_RECOVERY);
        Attack chosenAttack = choosePlayerAttack();
        if (chosenAttack == null) {
            playerBeast.recoverStamina(REST_RECOVERY);
            display.showRest(playerBeast, REST_RECOVERY);
        }
        else {
            useAttack(playerBeast, enemyBeast, chosenAttack);
        }

        if (!enemyBeast.isFainted()) {
            if (opponent.shouldRest()) {
                enemyBeast.recoverStamina(REST_RECOVERY);
                display.showRest(enemyBeast, REST_RECOVERY);
            }
            else {
                Attack enemyAttack = opponent.chooseAttack(random);
                useAttack(enemyBeast, playerBeast, enemyAttack);
            }
        }

        if (!playerBeast.isFainted()) {
            playerBeast.recoverStamina(STAMINA_REGEN);
        }

        if (!enemyBeast.isFainted()) {
            enemyBeast.recoverStamina(STAMINA_REGEN);
        }
    }

    private Attack choosePlayerAttack() {
        int restOption = playerBeast.getAttackCount() + 1;
        while (true) {
            int choice = input.readChoice("Choose move", 1, restOption);
            if (choice == restOption) {
                return null;
            }

            Attack attackChosen = playerBeast.getAttack(choice - 1);
            if (playerBeast.canUse(attackChosen)) {
                return attackChosen;
            }
            display.showError("Not enough stamina for " + attackChosen.getName() + " (needs " 
                + attackChosen.getStaminaCost() + ", you have " + playerBeast.getCurrentStamina() + ").");
        }
    }

    private void useAttack(Beast attacker, Beast defender, Attack attack) {
        attacker.useStamina(attack.getStaminaCost());
        int damage = calculator.calculateDamage(attack, defender);
        defender.takeDamage(damage);
        display.showAttack(attacker, attack, damage);
        display.showEffectiveness(calculator.getEffectiveness(attack.getType(), defender.getType()));
    }
}
