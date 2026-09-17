/**
 * Handles turn-based combat
 * 
 * @author kvasallo
 * @version Sep 17, 2026
 */
public class Battle {
    // ~ Fields ................................................................
    private static final int STAMINA_REGEN = 3;
    private static final int REST_STAMINA_RECOVERED = 7;

    // String types are placeholders for when the actual classes are built
    private String playerBeast;
    private String opponent;
    private String opponentBeast;
    private String inputHandler;
    private String display;
    private int turnNumber;
    private String calculator;

    // ~ Constructors ..........................................................
    /**
     * Create a new Battle object.
     * @param playerBeast 
     * @param opponent 
     * @param opponentBeast 
     * @param inputHandler 
     * @param display 
     * @param calculator 
     */
    public Battle(
        String playerBeast,
        String opponent,
        String opponentBeast,
        String inputHandler,
        String display,
        String calculator) {
        
        this.playerBeast = playerBeast;
        this.opponent = opponent;
        this.opponentBeast = opponentBeast;
        this.inputHandler = inputHandler;
        this.display = display;
        this.calculator = calculator;

    }


    // ----------------------------------------------------------
    /**
     * Adds dialogue and continually calls playTurn until a victor is determined
     * 
     * @return 
     */
    // ~Public Methods ........................................................
    public boolean run() {
        //false is returned for now to resolve compilation error
        return false;
    }


    /**
     * Runs through a single turn of a participant
     */
    private void playTurn() {

    }


    // ----------------------------------------------------------
    /**
     * Gets the number of turns that have been played thus far
     * 
     * @return numberOfTurnsElapsed number of turns that have been played
     */
    public int getTurnNumber() {
        return turnNumber;
    }
}
