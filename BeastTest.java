import static org.junit.Assert.*;
import org.junit.Test;
import student.TestCase;
public class BeastTest extends TestCase
{
    private Beast dummy;
    private Beast dummyTwo;
    
    public void setUp() {
        
        Attack[] attacks = { new Attack("Heavy Punch", 15, BeastType.NORMAL, 9),
            new Attack("Body Slam", 20, BeastType.NORMAL, 15),
            new Attack("Tackle", 8, BeastType.NORMAL, 2),
            new Attack("Jab", 6, BeastType.NORMAL, 1) };
        dummy = new Beast("Brawlet", BeastType.NORMAL, 40, 40, 25, attacks);
        
        dummyTwo = new Beast("Brawlet", BeastType.NORMAL, 40, 40, 25, attacks);
    }
    /**
     * test constructor exception handling
     */
    public void testBeast() {
        Attack[] attacks = { new Attack("Heavy Punch", 15, BeastType.NORMAL, 9),
            new Attack("Body Slam", 20, BeastType.NORMAL, 15),
            new Attack("Tackle", 8, BeastType.NORMAL, 2),
            new Attack("Jab", 6, BeastType.NORMAL, 1) };
        Attack[] nullAttack = { null, null, null, null };
        Exception eOne = null;
        Exception eTwo = null;
        Exception eThree = null;
        Exception eFour = null;
        Exception eFive = null;
        Exception eSix = null;
        Exception eSeven = null;
        try {
            Beast badBeast = new Beast(null, BeastType.NORMAL, 40, 40, 25, attacks);
        }
        catch (IllegalArgumentException e) {
            eOne = e;
        }
        try {
            Beast badBeast = new Beast("Brawlett", null, 40, 40, 25, attacks);
        }
        catch (IllegalArgumentException e) {
            eTwo = e;
        }
        try {
            Beast badBeast = new Beast("Brawlett", BeastType.NORMAL, 0, 40, 25, attacks);
        }
        catch (IllegalArgumentException e) {
            eThree = e;
        }
        try {
            Beast badBeast = new Beast("Brawlett", BeastType.NORMAL, 40, 0, 25, attacks);
        }
        catch (IllegalArgumentException e) {
            eFour = e;
        }
        try {
            Beast badBeast = new Beast("Brawlett", BeastType.NORMAL, 40, 40, 0, attacks);
        }
        catch (IllegalArgumentException e) {
            eFive = e;
        }
        try {
            Beast badBeast = new Beast("Brawlett", BeastType.NORMAL, 40, 40, 25, nullAttack);
        }
        catch (IllegalArgumentException e) {
            eSix = e;
        }
        try {
            Beast badBeast = new Beast("", BeastType.NORMAL, 40, 40, 25, attacks);
        }
        catch (IllegalArgumentException e) {
            eSeven = e;
        }
        assertNotNull(eOne);
        assertNotNull(eTwo);
        assertNotNull(eThree);
        assertNotNull(eFour);
        assertNotNull(eFive);
        assertNotNull(eSix);
        assertNotNull(eSeven);
        assertEquals("Beast name cannot be blank", eOne.getMessage());
        assertEquals("Type cannot be null", eTwo.getMessage());
        assertEquals("Health cannot be less than 0", eThree.getMessage());
        assertEquals("Stamina cannot be less than 0", eFour.getMessage());
        assertEquals("Speed cannot be less than 0", eFive.getMessage());
        assertEquals("Attacks cannot contain null", eSix.getMessage());
        assertEquals("Beast name cannot be blank", eSeven.getMessage());
        
        
    }
    /**
     * test the getter methods
     */
    @Test
    public void testGetters()
    {
        assertEquals ("Brawlet", dummy.getName());
        assertEquals (BeastType.NORMAL, dummy.getType());
        assertEquals(40, dummy.getCurrentHealth());
        assertEquals(40, dummy.getMaxHealth());
        assertEquals(40, dummy.getCurrentStamina());
        assertEquals(40, dummy.getMaxStamina());
        assertEquals(25, dummy.getSpeed());
        assertEquals(4, dummy.getAttackCount());
    }
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * test get attack method
     */
    public void testGetAttack() {
        Attack a = dummy.getAttack(0);
        assertEquals("Heavy Punch", a.getName());
        assertEquals(15, a.getPower());
        assertEquals(9, a.getStaminaCost());
        assertEquals(BeastType.NORMAL, a.getType());
    }
    /**
     * test get attack method exception hadnling
     */
    public void testGetAttackException() {
        Exception eOne = null;
        Exception eTwo = null;
        try {
            dummy.getAttack(-1);
        }
        catch (IndexOutOfBoundsException e) {
            eOne = e;
        }
        try {
            dummy.getAttack(67);
        }
        catch (IndexOutOfBoundsException e) {
            eTwo = e;
        }
        assertNotNull(eOne);
        assertNotNull(eTwo);
        assertEquals("Attack must be be 0-3", eOne.getMessage());
        assertEquals("Attack must be be 0-3", eTwo.getMessage());
    }
    /**
     * test takeDamage method, for regular value, value more than currentHealth
     * and exception handling
     */
    
    public void testTakeDamage() {
        Exception exception = null;
        try {
            dummy.takeDamage(-1);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        dummy.takeDamage(5);
        dummyTwo.takeDamage(100);
        assertNotNull(exception);
        assertEquals("Amount cannot be less than 0", exception.getMessage());
        assertEquals(35, dummy.getCurrentHealth());
        assertEquals(0, dummyTwo.getCurrentHealth());
    }
    /**
     * test isFainted method
     */
    public void testIsFainted() {
        dummy.takeDamage(40);
        assertTrue(dummy.isFainted());
        assertFalse(dummyTwo.isFainted());
    }
    /**
     * test useStamina method and exception handling
     */
    public void testUseStamina() {
        Exception eOne = null;
        Exception eTwo = null;
        try {
            dummy.useStamina(-1);
        }
        catch (IllegalArgumentException e) {
            eOne = e;
        }
        try {
            dummy.useStamina(100);
        }
        catch (IllegalStateException e) {
            eTwo = e;
        }
        dummy.useStamina(10);
        assertNotNull(eOne);
        assertNotNull(eTwo);
        assertEquals(30, dummy.getCurrentStamina());
        assertEquals("Stamina amount cannot be negative", eOne.getMessage());
        assertEquals("Not enough stamina", eTwo.getMessage());
    }
    /**
     * test canUse method and exception handling
     */
    public void testCanUse() {
        Exception exception = null;
        try {
            dummy.canUse(null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertTrue(dummy.canUse(dummy.getAttack(1)));
        dummyTwo.useStamina(35);
        assertFalse(dummyTwo.canUse(dummyTwo.getAttack(1)));
        assertNotNull(exception);
        assertEquals("Attack cannot be null", exception.getMessage());
    }
    /**
     * test recoverStamina method and exception handling
     */
    public void testRecoverStamina() {
        Exception exception = null;
        try {
            dummy.recoverStamina(-1);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        dummy.useStamina(10);
        dummyTwo.useStamina(10);
        dummy.recoverStamina(5);
        dummyTwo.recoverStamina(100);
        assertNotNull(exception);
        assertEquals("Stamina amount cannot be negative", exception.getMessage());
        assertEquals(35, dummy.getCurrentStamina());
        assertEquals(40, dummyTwo.getCurrentStamina());
        
        
    }
    /**
     * tests restoreAll method
     */
    public void testRestoreAll() {
        dummy.takeDamage(10);
        dummy.useStamina(10);
        dummy.restoreAll();
        assertEquals(40, dummy.getCurrentHealth());
        assertEquals(40, dummy.getCurrentStamina());
    }
    
    /**
     * tests toString method
     */
    public void testToString() {
        assertEquals("Brawlet [NORMAL] HP 40/40 SP 40/40", dummy.toString());
    }
    
}
