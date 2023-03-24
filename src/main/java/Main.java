import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Village village = new Village(new DatabaseConnection());

        while (true) {
            UserInput.printMenu(village.getDaysGone());

            int userInput = scanner.nextInt();
            UserInput.menuChoice(village, userInput);

            UserInput.printVillageInfo(village);

            if (village.castleisBuilt()) {
                System.exit(1);
            }
        }
    }
}




