import java.util.Scanner;

public class Main {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
ExpenseManager manager = new ExpenseManager();
boolean running = true;
// Displays the menu until the user chooses Exit
    while (running) {
     System.out.println("\n===== Personal Expense Tracker =====");
     System.out.println("1. Add Expense");
     System.out.println("2. View Expenses");
     System.out.println("3. Export CSV Report");
     System.out.println("4. Show Chart");
     System.out.println("5. Exit");
     System.out.print("Enter your choice: ");

     int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number from 1 to 5.");
            continue;
        }

    switch (choice) {
         case 1:
         handleAddExpense(scanner, manager);
         break;

         case 2:
         manager.viewExpenses();
         break;

         case 3:
        manager.exportCSV();
         break;

         case 4:
        manager.showChart();
         break;

         case 5:
        System.out.println("Goodbye!");
        running = false;
        break;

         default:
         System.out.println("Invalid choice! Please select a number from 1 to 5.");
        }
    }
    scanner.close();
}
 // Gets and validates the expense amount and category
private static void handleAddExpense(Scanner scanner, ExpenseManager manager) {
    double amount;

    while (true) {
        System.out.print("Enter amount: ");

        try {
            amount = Double.parseDouble(scanner.nextLine().trim());

            if (amount > 0 && !Double.isInfinite(amount)) {
                break;
            }

            System.out.println("Invalid amount! Amount must be greater than zero.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid numeric amount.");
        }
    }

    System.out.println("Choose category:");
    System.out.println("1. Food");
    System.out.println("2. Shopping");
    System.out.println("3. Transport");

    String category;

    while (true) {
        System.out.print("Enter category: ");
        String catChoice = scanner.nextLine().trim();

        switch (catChoice) {
            case "1":
                category = "Food";
                break;
            case "2":
                category = "Shopping";
                break;
            case "3":
                category = "Transport";
                break;
            default:
                System.out.println("Invalid category! Please choose 1, 2, or 3.");
                continue;
        }

        break;
    }

    manager.addExpense(category, amount);
    System.out.println("Expense added successfully!");
}
}
