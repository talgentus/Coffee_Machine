package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final String REMAINING = "remaining";
    private static final String BUY = "buy";
    private static final String FILL = "fill";
    private static final String TAKE = "take";
    public static final String EXIT = "exit";
    private static int waterAmount = 400;
    private static int milkAmount = 540;
    private static int beansAmount = 120;
    private static int cupsAmount = 9;
    private static int moneyAmount = 550;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String action;
        do {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            action = scanner.next();
            switch (action) {
                case REMAINING -> printState();
                case BUY -> buy(scanner);
                case FILL -> fill(scanner);
                case TAKE -> take();
                default -> {
                }
            }
        } while (!EXIT.equals(action));
    }

    private static void printState() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water%n", waterAmount);
        System.out.printf("%d ml of milk%n", milkAmount);
        System.out.printf("%d g of coffee beans%n", beansAmount);
        System.out.printf("%d disposable cups%n", cupsAmount);
        System.out.printf("$%d of money%n", moneyAmount);
    }

    private static void buy(Scanner scanner) {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String coffeeType = scanner.next();
        switch (coffeeType) {
            case "1" -> {
                if (areIngredientsSufficient(250, 0, 16)) {
                    waterAmount -= 250;
                    beansAmount -= 16;
                    moneyAmount += 4;
                    cupsAmount--;
                }
            }
            case "2" -> {
                if (areIngredientsSufficient(350, 75, 20)) {
                    waterAmount -= 350;
                    milkAmount -= 75;
                    beansAmount -= 20;
                    moneyAmount += 7;
                    cupsAmount--;
                }
            }
            case "3" -> {
                if (areIngredientsSufficient(200, 100, 12)) {
                    waterAmount -= 200;
                    milkAmount -= 100;
                    beansAmount -= 12;
                    moneyAmount += 6;
                    cupsAmount--;
                }
            }
            default -> {
            }
        }
    }

    private static boolean areIngredientsSufficient(int waterForCup, int milkForCup, int beansForCup) {
        if (waterAmount >= waterForCup && milkAmount >= milkForCup && beansAmount >= beansForCup) {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        } else if (waterAmount < waterForCup) {
            System.out.println("Sorry, not enough water!");
        } else if (milkAmount < milkForCup) {
            System.out.println("Sorry, not enough milk!");
        } else {
            System.out.println("Sorry, not enough coffee beans!");
        }
        return false;
    }

    private static void fill(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        waterAmount += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkAmount += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beansAmount += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        cupsAmount += scanner.nextInt();
    }

    private static void take() {
        System.out.printf("I gave you $%d%n", moneyAmount);
        moneyAmount = 0;
    }
}
