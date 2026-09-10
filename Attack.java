public class Attack
{
    //~ Fields ................................................................
    private final String name;
    private final int power;
    private final BeastType type;
    //~ Constructors ..........................................................
    public Attack(String name, int power, BeastType type) {
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
        this.name = name;
        this.power = power;
        this.type = type;
    }
    
   
    //~Public  Methods ........................................................
    public String getName() {
        return this.name;
    }
    
    public int getPower() {
        return this.power;
    }
    
    public BeastType getType() {
        return this.type;
    }
}
