/**
 * // -------------------------------------------------------------------------
/**
 *  One creature in the game. A beast has a name, and elemental type, health
 *  stamina, and 4 attacks
 *  
 *  Current health and stamina are the only values that can change during a battle
 * 
 *  @author rrahu
 *  @version Sep 17, 2026
 */
public class Beast
{
    //~ Fields ................................................................
    private final String name;
    private final BeastType type;
    private final int maxHealth;
    private final int maxStamina;
    private final Attack[] attacks;
    private int currentHealth;
    private int currentStamina;
    
    //~ Constructors ..........................................................
    /**
     * Creates a beast at full input and stamina
     */
    public Beast(String name, BeastType type, int maxHealth, int maxStamina 
           , Attack[] attacks) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Beast name cannot be blank");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Health cannot be less than 0");
        }
        if (maxStamina <= 0) {
            throw new IllegalArgumentException("Stamina cannot be less than 0");
        }
        for (Attack a : attacks) {
            if (a == null) {
                throw new IllegalArgumentException("Attacks cannot contain null");
            }
        }
        this.name = name;
        this.type = type;
        this.maxHealth = maxHealth;
        this.maxStamina = maxStamina;
        this.attacks = attacks.clone();
        this.currentHealth = maxHealth;
        this.currentStamina = maxStamina;
    }
    //~Public  Methods ........................................................

}
