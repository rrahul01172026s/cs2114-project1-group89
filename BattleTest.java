import java.util.Scanner;
import student.TestCase;

/**
 * Tests the Battle class
 * 
 * @author kvasallo
 * @version Sep 17, 2026
 */
public class BattleTest extends TestCase {
    // ~ Fields ................................................................
    private Battle battle;

    private Attack[] ATTACKS = { new Attack("Attack 1", 1, BeastType.FIRE, 1),
        new Attack("Attack 2", 2, BeastType.WATER, 1), new Attack("Attack 3", 3,
            BeastType.GRASS, 1), new Attack("Attack 4", 4, BeastType.NORMAL,
                1) };

    private Beast PLAYER_BEAST = new Beast("player beast", BeastType.GRASS, 100,
        100, 100, ATTACKS);
    
    private String OPPONENT = "The opponent the player is facing";
    
    private Beast OPPONENT_BEAST = new Beast("opp beast", BeastType.FIRE, 100,
        100, 1, ATTACKS);
    
    private Display DISPLAY = new Display(System.out);
    
    private InputHandler INPUT_HANDLER = new InputHandler(new Scanner("ph"),
        DISPLAY);

    private DamageCalculator CALCULATOR = new DamageCalculator();

    // ~Public Methods ........................................................
    public void setUp() {
        battle = new Battle(PLAYER_BEAST, OPPONENT, OPPONENT_BEAST,
            INPUT_HANDLER, DISPLAY, CALCULATOR);
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
