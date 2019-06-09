import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter command:");

        while (true) {
            String input = inputScanner.nextLine();  // Read user input
            if (!inputHandler.handleInput(input)) {
                break;
            }
        }
    }
}
