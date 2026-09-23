import java.util.Random;
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

    private Attack[] ATTACKS1 = { new Attack("Attack 1", 1, BeastType.FIRE, 1),
        new Attack("Attack 2", 2, BeastType.WATER, 1), new Attack("Attack 3", 3,
            BeastType.GRASS, 7), new Attack("Attack 4", 8, BeastType.NORMAL,
                1) };
    
    private Attack[] ATTACKS2 = { new Attack("Attack 1", 1, BeastType.FIRE, 4),
        new Attack("Attack 2", 2, BeastType.WATER, 4), new Attack("Attack 3", 3,
            BeastType.GRASS, 7), new Attack("Attack 4", 4, BeastType.NORMAL,
                4) };

    private Beast PLAYER_BEAST = new Beast("player beast", BeastType.GRASS, 10,
        5, 100, ATTACKS1);

    private Opponent OPPONENT = new Opponent("opp 1", new Beast("opp beast",
        BeastType.FIRE, 10, 5, 50, ATTACKS2), "enter battle", "opp won",
        "opp lose");

    private Display DISPLAY = new Display(System.out);

    private InputHandler INPUT_HANDLER = new InputHandler(new Scanner(
        "3\n5\n2\n4\n3\n1\n5\n2\n2\n2\n2\n2\n2"), DISPLAY);

    private DamageCalculator CALCULATOR = new DamageCalculator();

    private Random RANDOM = new Random();

    // ~Public Methods ........................................................
    public void setUp() {
        battle = new Battle(PLAYER_BEAST, OPPONENT, INPUT_HANDLER, DISPLAY,
            CALCULATOR, RANDOM);
    }


    /**
     * Test run method
     */
    public void testRun() {
        InputHandler input1 = new InputHandler(new Scanner(
            "3\n1\n4\n3\n2\n2\n3\n4\n4\n4\n4\n4"), DISPLAY);
        
        Battle battle1 = new Battle(PLAYER_BEAST, OPPONENT, input1,
            DISPLAY, CALCULATOR, RANDOM);
        
        Beast playerBeast2 = new Beast("player beast 2", BeastType.GRASS, 100,
            1, 25, ATTACKS2);
        
        Battle battle2 = new Battle(playerBeast2, OPPONENT, input1,
            DISPLAY, CALCULATOR, RANDOM);
        
        System.out.println("battle 1");
        assertTrue(BattleResult.PLAYER_WON.equals(battle1.run()));
        System.out.println("battle 2");
        assertTrue(BattleResult.PLAYER_WON.equals(battle.run()));
        System.out.println("battle 3");
        assertTrue(BattleResult.PLAYER_LOST.equals(battle2.run()));

    }


    /**
     * Tests getNumberTurn method
     */
    public void testGetTurnNumber() {
        assertEquals(0, battle.getTurnNumber());
    }


    /**
     * Tests getResult method
     */
    public void testGetResult() {
        
        InputHandler input1 = new InputHandler(new Scanner(
            "2\n2\n2\n2\n2\n2\n3\n4\n4\n4\n4\n4"), DISPLAY);
        
        Battle battle3 = new Battle(PLAYER_BEAST, OPPONENT, input1,
            DISPLAY, CALCULATOR, RANDOM);
        
        Exception error = null;
        
        try {
            battle3.getResult();
        }
        catch (Exception e) {
            error = e;
        }
        
        assertNotNull(error);
        battle3.run();
        error = null;
        try {
            battle.getResult();
        }
        catch (Exception e) {
            error = e;
        }
        assertNull(error);

    }
    
    public void testBattle() {
        Battle nullBattle;
        
        Exception error = null;
        
        try {
            nullBattle = new Battle(null, OPPONENT, INPUT_HANDLER, DISPLAY,
                CALCULATOR, RANDOM);
        }
        catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
        
        error = null;
        try {
            nullBattle = new Battle(PLAYER_BEAST, null, INPUT_HANDLER, DISPLAY,
                CALCULATOR, RANDOM);
        }
        catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
        
        error = null;
        try {
            nullBattle = new Battle(PLAYER_BEAST, OPPONENT, null, DISPLAY,
                CALCULATOR, RANDOM);
        }
        catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
        
        error = null;
        try {
            nullBattle = new Battle(PLAYER_BEAST, OPPONENT, INPUT_HANDLER, null,
                CALCULATOR, RANDOM);
        }
        catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
        
        error = null;
        try {
            nullBattle = new Battle(PLAYER_BEAST, OPPONENT, INPUT_HANDLER, DISPLAY,
                null, RANDOM);
        }
        catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
        
        error = null;
        try {
            nullBattle = new Battle(PLAYER_BEAST, OPPONENT, INPUT_HANDLER, DISPLAY,
                CALCULATOR, null);
        }
        catch (Exception e) {
            error = e;
        }
        assertNotNull(error);
    }
}
