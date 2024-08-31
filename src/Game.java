import java.util.ArrayList;

import static java.lang.System.console;

public class Game {
    private GameState gameState = GameState.IS_RUNNING;

    private int errorCount;

    private final String word;
    private String hiddenWord;

    private final ArrayList<Character> enteredLetters = new ArrayList<>();
    private final ArrayList<Character> wrongLetters = new ArrayList<>();

    ConsoleDrawer drawer = new ConsoleDrawer();

    public Game() {
        WordPicker wordPicker = new WordPicker();
        word = wordPicker.getRandomWord();

        hiddenWord = word.replaceAll("[а-я]", "_");
    }

    public void start() {
        while (true) {
            gameState = checkGameState();

            ConsoleDrawer.clearScreen();
            console().printf("Error count: %d \n", errorCount);
            console().printf("Wrong letters: %s \n", String.join(",", wrongLetters
                    .toString()
                    .replace("[", "")
                    .replace("]", "")));

            if (gameState != GameState.WIN) {
                console().printf(ConsoleDrawer.ASCII_GALLOW_SPRITES.get(errorCount));
            } else {
                console().printf(ConsoleDrawer.ASCII_GALLOW_SPRITES.getLast());
            }

            drawer.outputHiddenWordState(hiddenWord);

            if (gameState == GameState.WIN) {
                console().printf(ConsoleDrawer.ANSI_WIN_MESSAGE_COLOR
                        + "You win!\n\n"
                        + ConsoleDrawer.ANSI_RESET_COLOR);
                break;
            } else if (gameState == GameState.HANGED) {
                console().printf(ConsoleDrawer.ANSI_HANGED_MESSAGE_COLOR
                        + "You hanged! The word was: %s\n\n"
                        + ConsoleDrawer.ANSI_RESET_COLOR, word);
                break;
            }

            char enteredLetter = getLetterFromUserInput();
            enteredLetters.add(enteredLetter);

            if (word.contains(String.valueOf(enteredLetter))) {
                openLetterInHiddenWord(enteredLetter);
            } else {
                errorCount++;
                wrongLetters.add(enteredLetter);
            }
        }
    }

    private GameState checkGameState() {
        if (errorCount == 6) {
            return GameState.HANGED;
        }
        if (hiddenWord.equals(word)) {
            return GameState.WIN;
        }

        return GameState.IS_RUNNING;
    }

    private char getLetterFromUserInput() throws NullPointerException{
        while (true) {
            console().printf("Enter a letter:\n");

            String input;

            input = console().readLine();

            if (isInputValid(input)) {
                return Character.toLowerCase(input.charAt(0));
            }
        }
    }

    private boolean isInputValid(String input) {
        if (input.matches("^[а-яА-Я]$")) {
            if (enteredLetters.contains(Character.toLowerCase(input.charAt(0)))) {
                console().printf(ConsoleDrawer.ANSI_WARNING_MESSAGE_COLOR
                        + "You have already entered this letter!\n\n"
                        + ConsoleDrawer.ANSI_RESET_COLOR);

                return false;
            }

            return true;
        } else {
            console().printf(ConsoleDrawer.ANSI_WARNING_MESSAGE_COLOR
                    + "You can only enter one character in [а-яА-Я]!\n\n"
                    + ConsoleDrawer.ANSI_RESET_COLOR);

            return false;
        }
    }

    private void openLetterInHiddenWord(char enteredLetter) {
        StringBuilder sb = new StringBuilder(hiddenWord);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == enteredLetter) {
                sb.setCharAt(i, enteredLetter);
            }
        }
        hiddenWord = sb.toString();
    }
}