import student.TestCase;

/**
 *  Tests the Battle class
 * 
 *  @author kvasallo
 *  @version Sep 17, 2026
 */
public class BattleTest extends TestCase {
    //~ Fields ................................................................
    private Battle battle;
    
    private String PLAYER_BEAST = "The player's beast";
    private String OPPONENT = "The opponent the play is facing";
    private String OPPONENT_BEAST = "The beast the opponent has";
    private String INPUT_HANDLER = "Handles input";
    private String DISPLAY = "Displays stuff";
    private String CALCULATOR = "Calculates stuff";

    //~Public  Methods ........................................................
    public void setUp() {
        battle = new Battle(PLAYER_BEAST, OPPONENT, OPPONENT_BEAST, INPUT_HANDLER, DISPLAY, CALCULATOR);
    }

    /**
     * Test run method
     */
    public void testRun() {
        
    }

    /**
     * Tests getNumberTurn method
     */
    public void testGetNumberTurn() {
        
    }
}
