// -------------------------------------------------------------------------
/**
 * Calculates how much damage type relations do to each other
 * 
 * @author kvasallo
 * @version Sep 17, 2026
 */
public class DamageCalculator {
    // ~ Fields ................................................................
    private double ADVANTAGE_MULT = 2.0;
    private double NEUTRAL_MULT = 1.0;
    private double DISADVANTAGE_MULT = 0.5;

    // ~Public Methods ........................................................
    /**
     * Calculate the damage taken by a Beast
     * 
     * @param attack
     *            The incoming attack
     * @param defender
     *            The beast receiving the attack
     * @return damage Damage of the attack after effectiveness calculation
     */
    public int calculateDamage(Attack attack, Beast defender) {
        if (attack == null || defender == null) {
            throw new IllegalArgumentException("One of the arguments are null");
        }
        double multiplier = getEffectiveness(attack.getType(), defender
            .getType());

        return Math.max(1, (int)Math.round(attack.getPower() * multiplier));
    }


    // ----------------------------------------------------------
    /**
     * Determine the effectiveness of the attack
     * 
     * @param attackType
     *            BeastType of the Attack being performed
     * @param defenderType
     *            BeastType of the Beast being attacked
     * @return damageMultiplier The ratio if the attack is effective, resisted,
     *         or neither
     */
    public double getEffectiveness(
        BeastType attackType,
        BeastType defenderType) {
        if (attackType == null || defenderType == null) {
            throw new IllegalArgumentException("One of the arguments are null");
        }
        if (beats(attackType, defenderType)) {
            return ADVANTAGE_MULT;
        }
        if (beats(defenderType, attackType)) {
            return DISADVANTAGE_MULT;
        }
        return NEUTRAL_MULT;
    }


    private boolean beats(BeastType attackType, BeastType defenderType) {
        // Fire beats grass
        if (attackType == BeastType.FIRE && defenderType == BeastType.GRASS) {
            return true;
        }
        // Grass beats water
        if (attackType == BeastType.GRASS && defenderType == BeastType.WATER) {
            return true;
        }
        // Water beats fire
        if (attackType == BeastType.WATER && defenderType == BeastType.FIRE) {
            return true;
        }
        return false;
    }

}
