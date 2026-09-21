import java.util.Scanner;

/**
 * Reads everything the player types. Every menu choice goes through this
 * class, which re-prompts until a valid number is entered, so bad input
 * never crashes the game.
 */
public class InputHandler {
    private final Scanner scanner;
    private final Display display;

    /**
     * @param scanner where input is read from
     * @param display used to print prompts and error messages
     * @throws IllegalArgumentException if either argument is null
     */
    public InputHandler(Scanner scanner, Display display) {
        if (scanner == null || display == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.scanner = scanner;
        this.display = display;
    }

    /**
     * Asks for a number and keeps asking until the player enters a whole
     * number within range. Blank lines, letters, symbols, and out-of-range
     * numbers all produce an error message and a new prompt.
     *
     * @param prompt what to ask the player
     * @param min    lowest allowed value
     * @param max    highest allowed value
     * @return a valid number from min to max
     * @throws IllegalArgumentException if min is greater than max
     */
    public int readChoice(String prompt, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max");
        }
        while (true) {
            display.showPrompt(prompt + " (" + min + "-" + max + ")");
            String line = scanner.nextLine().trim();   // NoSuchElementException if input ends
            if (line.isEmpty()) {
                display.showError("please type a number.");
                continue;
            }
            int value;
            try {
                value = Integer.parseInt(line);
            }
            catch (NumberFormatException e) {
                display.showError("\"" + line + "\" is not a whole number. Enter "
                    + min + " to " + max + ".");
                continue;
            }
            if (value < min || value > max) {
                display.showError("please enter a number from " + min + " to " + max + ".");
                continue;
            }
            return value;
        }
    }
}