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
    private static final int ATTACK_COUNT = 4;
    
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
    /** @return the Beast's name */
    public String getName() { return name; }
    /** @return the Beast's elemental type */
    public BeastType getType() { return type; }
    /** @return current health, always 0 to maxHealth */
    public int getCurrentHealth() { return currentHealth; }
    /** @return maximum health */
    public int getMaxHealth() { return maxHealth; }
    /** @return current stamina, always 0 to maxStamina */
    public int getCurrentStamina() { return currentStamina; }
    /** @return maximum stamina */
    public int getMaxStamina() { return maxStamina; }
    
    /**
     * @param index a 0 based position from 0-3
     * @return the attack at that position
     * @throws IndexOutofBoundsException if index is not 0-3
     * 
     */
    public Attack getAttack(int index) {
        if (index < 0 || index >= ATTACK_COUNT) {
            throw new IndexOutOfBoundsException("Attack must be be 0-3");
        }
        return attacks[index];
    }
    /**
     * @return 4, the number of attacks every Beast has
     */
    public int getAttackCount() {
        return ATTACK_COUNT;
    }
    /**
     * Lowers health by the given amount, stopping at 0
     * 
     * @param amount damage to take; cannot be negative
     * @throws IllegalArgumentException if amount is negative
     */
    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be less than 0");
        }
        currentHealth = Math.max(0, currentHealth - amount);
    }
    /**
     * @return true when health has reached 0
     */
    public boolean isFainted() {
        return currentHealth == 0;
    }
    public boolean canUse(Attack attack) {
        if (attack == null) {
            throw new IllegalArgumentException("Attack cannot be null");
        }
        return currentStamina >= attack.getStaminaCost();
    }
    /**
     * Spends stamina on a move.
     *
     * @param amount stamina to spend; cannot be negative
     * @throws IllegalArgumentException if amount is negative
     * @throws IllegalStateException if there is not enough stamina
     */
    public void useStamina(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Stamina amount cannot be negative");
        }
        if (amount > currentStamina) {
            throw new IllegalStateException("Not enough stamina");
        }
        currentStamina -= amount;
    }
    
    /**
     * Adds stamina, never going above the maximum.
     *
     * @param amount stamina to add; cannot be negative
     * @throws IllegalArgumentException if amount is negative
     */
    public void recoverStamina(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Stamina amount cannot be negative");
        }
        currentStamina = Math.min(maxStamina, currentStamina + amount);
    }
    /**
     * Refills health and stamina to max
     */
    public void restoreAll() {
        currentHealth = maxHealth;
        currentStamina = maxStamina;
    }
    /**
     * @return a status line for the beast
     */
    @Override
    public String toString() {
        return name + " [" + type + "] HP " + currentHealth + "/" + maxHealth
            + " SP " + currentStamina + "/" + maxStamina;
    }
    
}
