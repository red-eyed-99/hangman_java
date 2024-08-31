import org.fusesource.jansi.AnsiConsole;

import static java.lang.System.console;

public class Main {
    public static void main(String[] args) {
        if (System.console() == null) {
            System.out.println("Console unavailable");
            System.exit(1);
        }

        AnsiConsole.systemInstall();
        ConsoleDrawer.clearScreen();

        console().printf("Welcome to H_A_N_G_M_A_N\n\n");

        while (true) {
            console().printf("1. Start new game 2. Exit\n");

            String input;

            input = console().readLine();

            try {
                switch (input) {
                    case "1":
                        Game game = new Game();
                        game.start();
                        break;
                    case "2":
                        System.exit(0);
                        break;
                    default:
                        console().printf(ConsoleDrawer.ANSI_WARNING_MESSAGE_COLOR
                                + "Enter \"1\" to start new game or \"2\" to exit\n\n"
                                + ConsoleDrawer.ANSI_RESET_COLOR);
                        break;
                }
            } catch (NullPointerException _) {
                console().printf(ConsoleDrawer.ANSI_ERROR_MESSAGE_COLOR
                        + "Exiting..."
                        + ConsoleDrawer.ANSI_RESET_COLOR);

                System.exit(1);
            }
        }
    }
}
