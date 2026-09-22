import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * // -------------------------------------------------------------------------
/**
 *  One NPC opponent: Contain their name, specific dialogue, the beast they 
 *  battle with and how they decide their moves
 * 
 *  @author rrahu
 *  @version Sep 21, 2026
 */
public class Opponent
{
    //~ Fields ................................................................
    private final String name;
    private final Beast beast;
    private final String introLine;
    private final String winLine;
    private final String loseLine;

    //~ Constructors ..........................................................
    /**
     * Creates a trainer.
     *
     * @param name      the trainer's name
     * @param beast     the Beast they battle with
     * @param introLine what they say when the battle starts
     * @param winLine   what they say if they win
     * @param loseLine  what they say if they lose
     * @throws IllegalArgumentException if any argument is null
     */
    public Opponent(String name, Beast beast, String introLine, String winLine, String loseLine) {
        if (name == null || beast == null || introLine == null
            || winLine == null || loseLine == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.name = name;
        this.beast = beast;
        this.introLine = introLine;
        this.winLine = winLine;
        this.loseLine = loseLine;
        
    }
    //~Public  Methods ........................................................
    /** @return the trainer's name */
    public String getName() { return name; }
    /** @return the trainer's Beast */
    public Beast getBeast() { return beast; }
    /** @return what the trainer says when the battle starts */
    public String getIntroLine() { return introLine; }
    /** @return what the trainer says if they win */
    public String getWinLine() { return winLine; }
    /** @return what the trainer says if they lose */
    public String getLoseLine() { return loseLine; }
    
    /**
     * @return if the opponenet's beast does not have enough stamina
     * for any of its moves
     */
    public boolean shouldRest() {
        for (int i = 0; i < beast.getAttackCount(); i++) {
            if (beast.canUse(beast.getAttack(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Picks a random move the Beast can currently afford.
     *
     * @param random the source of randomness
     * @return an affordable attack
     * @throws IllegalArgumentException if random is null
     * @throws IllegalStateException if no move is affordable
     */

    public Attack chooseAttack(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }
        List<Attack> affordable = new ArrayList<>();
        for (int i = 0; i < beast.getAttackCount(); i++) {
            Attack a = beast.getAttack(i);
            if (beast.canUse(a)) {
                affordable.add(a);
            }
        }
        if (affordable.isEmpty()) {
            throw new IllegalStateException("Beast has no usable moves");
        }
        return affordable.get(random.nextInt(affordable.size()));
    }
}
