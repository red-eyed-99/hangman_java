import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.console;

public class WordPicker {
    private final ArrayList<String> words = new ArrayList<>();

    private final Random random = new Random();

    public WordPicker() {
        fillWordList();
    }

    private void fillWordList() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("words/words.txt")))) {
            String word;

            while ((word = reader.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException | NullPointerException ex) {
            console().printf(ConsoleDrawer.ANSI_ERROR_MESSAGE_COLOR
                    + "Failed to read file \"words.txt\"\n"
                    + ConsoleDrawer.ANSI_RESET_COLOR);

            System.exit(1);
        }
    }

    public String getRandomWord() {
        int wordIndex = random.nextInt(words.size());
        return words.get(wordIndex);
    }
}
