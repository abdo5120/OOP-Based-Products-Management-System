package main;

import java.util.Scanner;

// ANSI color codes for console output
enum Colors {
    BLACK("\u001B[30m"),
    BLUE("\u001B[34m"),
    GREEN("\u001B[32m"),
    RED("\u001B[31m"),
    WHITE("\u001B[37m"),
    LIGHT_BLUE("\u001B[94m"),
    LIGHT_GREEN("\u001B[92m"),
    LIGHT_RED("\u001B[91m"),
    BRIGHT_WHITE("\u001B[97m"),
    RESET("\u001B[0m");

    private final String code;

    Colors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

// Utility class for console operations
class TerminalUtils
{
    private static final Scanner scanner = new Scanner(System.in);

    // Set console color (using ANSI codes)
    public static void setColor(Colors color) {
        System.out.print(color.getCode());
    }

    // Get input with specified color
    public static <T> T getInput(Colors color, Class<T> type) {
        setColor(color);
        T input;
        if (type == String.class) {
            input = type.cast(scanner.nextLine());
        } else if (type == Integer.class) {
            input = type.cast(scanner.nextInt());
            scanner.nextLine(); // Consume newline
        } else if (type == Double.class) {
            input = type.cast(scanner.nextDouble());
            scanner.nextLine(); // Consume newline
        } else {
            throw new IllegalArgumentException("Unsupported input type");
        }
        setColor(Colors.RESET);
        return input;
    }

    // Clear console (platform-independent approximation)
    public static void clearTerminal() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    // Convert string to lowercase
    public static String convertStringToLower(String s) {
        return s.toLowerCase();
    }

    // Prompt for retry
    public static boolean tryAgain() {
        setColor(Colors.BRIGHT_WHITE);
        System.out.print("Do You Want To Try Again? Yes:No => ");
        String option = getInput(Colors.RED, String.class);
        System.out.println();
        return convertStringToLower(option).equals("yes");
    }

    // Pause until a key is pressed
    public static void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}

