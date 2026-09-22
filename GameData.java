import java.util.ArrayList;
import java.util.List;

/**
 * // -------------------------------------------------------------------------
 * /** Builds all the data in the game: the beasts, attacks, opponents, and
 * starter beasts. All of the games numbers reside in this file, so to change
 * the games difficulty only edits to this file need to be made
 * 
 * @author rrahu
 * @version Sep 21, 2026
 */
public class GameData
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * creates the starter beasts for the game
     * 
     * @return a list of the starter beasts
     */
    public List<Beast> createStarters()
    {
        List<Beast> starters = new ArrayList<>();
        starters.add(createFireChimp());
        starters.add(createTidalot());
        starters.add(createSproutling());
        starters.add(createBrawlet());
        return starters;
    }


    /**
     * @return a list of opponents in the order they're fought.
     */
    public List<Opponent> createOpponents()
    {
        List<Opponent> opponents = new ArrayList<>();
        opponents.add(
            new Opponent(
                "Rookie Riley",
                createBrawlet(),
                "Rookie Riley: \"I just got my first Beast too! Let's see who trained harder!\"",
                "Rookie Riley: \"I won?! I can't believe it!\"",
                "Rookie Riley: \"Aw, you're really good. Go get 'em!\""));
        opponents.add(
            new Opponent(
                "Captain Marlow",
                createFireChimp(),
                "Captain Marlow: \"Many challengers have sunk against my Fire Chimp. Ready to burn?\"",
                "Captain Marlow: \"Couldn't handle the heat. Better luck next voyage.\"",
                "Captain Marlow: \"You've withstood the heat. The Champion awaits.\""));
        opponents.add(
            new Opponent(
                "Champion Vex",
                createSproutling(),
                "Champion Vex: \"So you're the one everyone's talking about. Show me.\"",
                "Champion Vex: \"Come back when you're ready to be a champion.\"",
                "Champion Vex: \"Incredible. The title is yours.\""));
        return opponents;

    }


    private Beast createFireChimp()
    {
        Attack[] attacks = { new Attack("Flame shot", 12, BeastType.FIRE, 5),
            new Attack("Fire roar", 16, BeastType.FIRE, 8),
            new Attack("Quick shot", 8, BeastType.NORMAL, 3),
            new Attack("Headbutt", 10, BeastType.NORMAL, 5) };
        return new Beast("Fire Chimp", BeastType.FIRE, 44, 30, 20, attacks);
    }


    private Beast createTidalot()
    {
        Attack[] attacks = { new Attack("Aquajet", 10, BeastType.WATER, 5),
            new Attack("Surf", 14, BeastType.WATER, 8),
            new Attack("Quick shot", 8, BeastType.NORMAL, 3),
            new Attack("Headbutt", 10, BeastType.NORMAL, 5) };
        return new Beast("Tidalot", BeastType.FIRE, 50, 40, 15, attacks);
    }


    private Beast createSproutling()
    {
        Attack[] attacks = { new Attack("Stick Throw", 9, BeastType.GRASS, 5),
            new Attack("Razor Leaf", 17, BeastType.GRASS, 10),
            new Attack("Quick shot", 8, BeastType.NORMAL, 3),
            new Attack("Headbutt", 10, BeastType.NORMAL, 5) };
        return new Beast("Sproutling", BeastType.GRASS, 55, 30, 10, attacks);
    }


    private Beast createBrawlet()
    {
        Attack[] attacks = { new Attack("Heavy Punch", 15, BeastType.NORMAL, 9),
            new Attack("Body Slam", 20, BeastType.NORMAL, 15),
            new Attack("Tackle", 8, BeastType.NORMAL, 2),
            new Attack("Jab", 6, BeastType.NORMAL, 1) };
        return new Beast("Brawlet", BeastType.NORMAL, 40, 40, 25, attacks);
    }

}
