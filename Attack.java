public class Attack
{
    //~ Fields ................................................................
    private final String name;
    private final int power;
    private final BeastType type;
    private final int staminaCost;
    //~ Constructors ..........................................................
    public Attack(String name, int power, BeastType type, int staminaCost) {
        if (name == null) {
            throw new IllegalArgumentException("Attack name cannot be null");
        }
        if (power <= 0) {
            throw new IllegalArgumentException("Attack power cannot be less"
                + " than 0");
        }
        if (type == null) {
            throw new IllegalArgumentException("Attack type cannot be null");
        }
        if (staminaCost < 0) {
            throw new IllegalArgumentException("Stamina cost cannot be less" 
                + " than 0");
        }
        this.name = name;
        this.power = power;
        this.type = type;
        this.staminaCost = staminaCost;
        
        
    }
    
   
    //~Public  Methods ........................................................
    /**
     * @return the move's name
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return the moves power
     */
    public int getPower() {
        return this.power;
    }
    /**
     * @return the moves type
     */
    public BeastType getType() {
        return this.type;
    }
    /**
     * @return the move's stamina cost
     */
    public int getStaminaCost() {
        return this.staminaCost;
    }
    /**
     * @return a menu label for the attack
     */
    @Override
    public String toString() {
        return name + " (" + type + ", Power " + power + ", Cost " 
            + staminaCost + ")";
    }
}
