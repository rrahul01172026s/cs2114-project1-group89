import static org.junit.Assert.*;
import org.junit.Test;
import student.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  test class for the attack class
 * 
 *  @author rrahu
 *  @version Sep 22, 2026
 */
public class AttackTest extends TestCase
{
    private Attack a;
    
    public void setUp() {
        a = new Attack("Heavy Punch", 15, BeastType.NORMAL,9);
    }
    @Test
    /**
     * tests exception handling of constructor
     */
    public void testAttack()
    {
        Exception eOne = null;
        Exception eTwo = null;
        Exception eThree = null;
        Exception eFour = null;
        try {
            Attack b = new Attack(null, 10, BeastType.NORMAL, 10);
        }
        catch (IllegalArgumentException e) {
            eOne = e;
        }
        try {
            Attack b = new Attack("Scratch", 0, BeastType.NORMAL, 10);
        }
        catch (IllegalArgumentException e) {
            eTwo = e;
        }
        try {
            Attack b = new Attack("Scratch", 10, null, 10);
        }
        catch (IllegalArgumentException e) {
            eThree = e;
        }
        try {
            Attack b = new Attack("Scratch", 10, BeastType.NORMAL, -1);
        }
        catch (IllegalArgumentException e) {
            eFour = e;
        }
        assertNotNull(eOne);
        assertNotNull(eTwo);
        assertNotNull(eThree);
        assertNotNull(eFour);
        assertEquals("Attack name cannot be null", eOne.getMessage());
        assertEquals("Attack power cannot be less than 0", eTwo.getMessage());
        assertEquals("Attack type cannot be null", eThree.getMessage());
        assertEquals("Stamina cost cannot be less than 0", eFour.getMessage());
    }
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * test the getter methods
     */
    public void testGettters() {
        assertEquals("Heavy Punch", a.getName());
        assertEquals(15, a.getPower());
        assertEquals(BeastType.NORMAL, a.getType());
        assertEquals(9, a.getStaminaCost());
    }
    /**
     * tests the toString method
     */
    public void testToString() {
        assertEquals("Heavy Punch (NORMAL, Power 15, Cost 9)", a.toString());
    }
    
}
