import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.console;
import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleDrawer {
    public static final String ANSI_CLEAR_SCREEN = "\033[H\033[2J";

    public static final Ansi ANSI_RESET_COLOR = ansi().reset();

    public static final Ansi ANSI_WARNING_MESSAGE_COLOR = ansi().fgBrightYellow();
    public static final Ansi ANSI_ERROR_MESSAGE_COLOR = ansi().fgBrightRed();
    public static final Ansi ANSI_WIN_MESSAGE_COLOR = ansi().fgBrightGreen();
    public static final Ansi ANSI_HANGED_MESSAGE_COLOR = ansi().fgBrightRed();

    public static final Ansi ANSI_HIDDEN_WORD_COLOR = ansi().fgBrightBlue();

    public static final ArrayList<String> ASCII_GALLOW_SPRITES = new ArrayList<>(Arrays.asList(
            """
                    ___________
                    |/        |
                    |
                    |
                    |
                    |
                    |
                    --------
                    """,
            """
                    ___________
                    |/        |
                    |         o
                    |
                    |
                    |
                    |
                    --------
                    """,
            """
                    ___________
                    |/        |
                    |         o
                    |         O
                    |
                    |
                    |
                    --------
                    """,
            """
                    ___________
                    |/        |
                    |         o
                    |        /O
                    |
                    |
                    |
                    --------
                    """,
            """
                    ___________
                    |/        |
                    |         o
                    |        /O\\
                    |
                    |
                    |
                    --------
                    """,
            """
                    ___________
                    |/        |
                    |         o
                    |        /O\\
                    |        /
                    |
                    |
                    --------
                    """,
            """
                    ___________
                    |/        |
                    |         o
                    |        /O\\
                    |        / \\
                    |
                    |
                    --------
                    """,
            """
                    ___________
                    |/        |
                    |
                    |
                    |        \\o/
                    |         O
                    |        / \\
                    --------
                    """));

    public void outputHiddenWordState(String hiddenWord) {
        char[] hiddenWordLetters = hiddenWord.toCharArray();
        for (int i = 0; i < hiddenWordLetters.length; i++) {
            if (i == hiddenWordLetters.length - 1) {
                console().printf(ANSI_HIDDEN_WORD_COLOR
                        + String.valueOf(hiddenWordLetters[i])
                        + ANSI_RESET_COLOR + "\n\n");
            } else {
                console().printf(ANSI_HIDDEN_WORD_COLOR
                        + String.valueOf(hiddenWordLetters[i]) + ' '
                        + ANSI_RESET_COLOR);
            }
        }
    }

    public static void clearScreen() {
        try {
            // for windows
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // for unix systems
                System.out.print(ANSI_CLEAR_SCREEN);
                System.out.flush();
            }

        } catch (Exception e) {
            System.console().printf(ANSI_ERROR_MESSAGE_COLOR
                    + "Failed to clear screen"
                    + ANSI_RESET_COLOR);
        }
    }
}
