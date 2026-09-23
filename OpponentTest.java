import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import student.TestCase;

public class OpponentTest
{
    private Opponent o;
    private Beast beast;
    
    
    public void setUp() {
        Attack[] attacks = { new Attack("Flame shot", 12, BeastType.FIRE, 5),
            new Attack("Fire roar", 16, BeastType.FIRE, 8),
            new Attack("Quick shot", 8, BeastType.NORMAL, 3),
            new Attack("Headbutt", 10, BeastType.NORMAL, 5) };
        beast = new Beast("Fire Chimp", BeastType.FIRE, 44, 30, 20, attacks);
        
        o = new Opponent(
            "Rookie Riley",
            beast,
            "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
            "Rookie Riley: \"I won?! I can't believe it!\"",
            "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
    }
    
    /**
     * test exception handling of constructor
     */
    @Test
    public void testOpponent()
    {
        Exception eOne = null;
        Exception eTwo = null;
        Exception eThree = null;
        Exception eFour = null;
        Exception eFive = null;
        
        try {
            Opponent dummy = new Opponent(
                null,
                beast,
                "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
                "Rookie Riley: \"I won?! I can't believe it!\"",
                "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
        }
        catch (IllegalArgumentException e) {
            eOne = e;
        }
        try {
            Opponent dummy = new Opponent(
                "Rookie Riley",
                null,
                "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
                "Rookie Riley: \"I won?! I can't believe it!\"",
                "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
        }
        catch (IllegalArgumentException e) {
            eTwo = e;
        }
        try {
            Opponent dummy = new Opponent(
                "Rookie Riley",
                beast,
                null,
                "Rookie Riley: \"I won?! I can't believe it!\"",
                "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
        }
        catch (IllegalArgumentException e) {
            eThree = e;
        }
        try {
            Opponent dummy = new Opponent(
                "Rookie Riley",
                beast,
                "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
                null,
                "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
        }
        catch (IllegalArgumentException e) {
            eFour = e;
        }
        try {
            Opponent dummy = new Opponent(
                "Rookie Riley",
                beast,
                "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
                "Rookie Riley: \"I won?! I can't believe it!\"",
                null);
        }
        catch (IllegalArgumentException e) {
            eFive = e;
        }
        assertNotNull(eOne);
        assertNotNull(eTwo);
        assertNotNull(eThree);
        assertNotNull(eFour);
        assertNotNull(eFive);
        assertEquals("Arguments cannot be null", eOne.getMessage());
        assertEquals("Arguments cannot be null", eTwo.getMessage());
        assertEquals("Arguments cannot be null", eThree.getMessage());
        assertEquals("Arguments cannot be null", eFour.getMessage());
        assertEquals("Arguments cannot be null", eFive.getMessage());
    }
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * test getters
     */
    public void testGetters() {
        Beast oBeast = o.getBeast();
        assertEquals("Rookie Riley", o.getName());
        assertEquals("Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"", o.getIntroLine());
        assertEquals( "Rookie Riley: \"I won?! I can't believe it!\"", o.getWinLine());
        assertEquals("Rookie Riley: \"Aw, you're really good. Go get 'em!\"", o.getLoseLine());
        assertEquals("Fire Chimp", oBeast.getName());
        assertEquals(BeastType.FIRE, oBeast.getType());
        assertEquals(44, oBeast.getMaxHealth());
        assertEquals(30, oBeast.getMaxStamina());
        assertEquals(20, oBeast.getSpeed());
    }
    /**
     * test shouldRest method, when it should return false
     */
    public void testShouldRestFalse() {
        assertFalse(o.shouldRest());
    }
    /**
     * test shouldRest method, when it should return true
     */
    public void testShouldRestTrue() {
        Attack[] attacks = { new Attack("Flame shot", 12, BeastType.FIRE, 5),
            new Attack("Fire roar", 16, BeastType.FIRE, 8),
            new Attack("Quick shot", 8, BeastType.NORMAL, 3),
            new Attack("Headbutt", 10, BeastType.NORMAL, 5) };
        beast = new Beast("Fire Chimp", BeastType.FIRE, 44, 1, 20, attacks);
        
        Opponent dummy = new Opponent(
            "Rookie Riley",
            beast,
            "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
            "Rookie Riley: \"I won?! I can't believe it!\"",
            "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
        assertTrue(dummy.shouldRest());
    }
    /**
     * test chooseAttack when it should throw an IAE
     */
    public void testChooseAttackIAE() {
        Exception exception = null;
        try {
            o.chooseAttack(null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Random cannot be null", exception.getMessage());
    }
    /**
     * test chooseAttack when it should throw an ISE
     */
    public void testChooseAttackISE() {
        Exception exception = null;
        Attack[] attacks = { new Attack("Flame shot", 12, BeastType.FIRE, 5),
            new Attack("Fire roar", 16, BeastType.FIRE, 8),
            new Attack("Quick shot", 8, BeastType.NORMAL, 3),
            new Attack("Headbutt", 10, BeastType.NORMAL, 5) };
        beast = new Beast("Fire Chimp", BeastType.FIRE, 44, 1, 20, attacks);
        
        Opponent dummy = new Opponent(
            "Rookie Riley",
            beast,
            "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
            "Rookie Riley: \"I won?! I can't believe it!\"",
            "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
        Random random = new Random();
        try {
            dummy.chooseAttack(random);
        }
        catch (IllegalStateException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals("Beast has no usable moves", exception.getMessage());
    }
    
    /**
     * test choose attack when it can return a method
     */
    public void testChooseAttack() {
        Attack[] attacks = { new Attack("Flame shot", 12, BeastType.FIRE, 5),
            new Attack("Fire roar", 16, BeastType.FIRE, 1),
            new Attack("Quick shot", 8, BeastType.NORMAL, 3),
            new Attack("Headbutt", 10, BeastType.NORMAL, 5) };
        beast = new Beast("Fire Chimp", BeastType.FIRE, 44, 1, 20, attacks);
        
        Opponent dummy = new Opponent(
            "Rookie Riley",
            beast,
            "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
            "Rookie Riley: \"I won?! I can't believe it!\"",
            "Rookie Riley: \"Aw, you're really good. Go get 'em!\"");
        Random random = new Random();
        Attack a = dummy.chooseAttack(random);
        assertEquals("Fire roar", a.getName());
    }
}
