import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExpenseManager implements Reportable {
// Stores all expenses using ArrayList
private ArrayList<Expense> expenses;

private static final String[] CATEGORIES = {
    "Food", "Shopping", "Transport"
};
public ExpenseManager() {
    expenses = new ArrayList<>();
}
// Validates and adds a new expense
public void addExpense(String category, double amount) {
    if (category == null || amount <= 0
            || Double.isNaN(amount) || Double.isInfinite(amount)) {
        System.out.println("Invalid expense.");
        return;
    }
    boolean validCategory = false;

    for (String item : CATEGORIES) {
        if (item.equalsIgnoreCase(category)) {
            category = item;
            validCategory = true;
            break;
        }
    }
    if (!validCategory) {
        System.out.println("Invalid category.");
        return;
    }
    expenses.add(new Expense(category, amount));
}
  // Calculates the total for a specific category
public double getCategoryTotal(String category) {
    double total = 0;

    for (Expense e : expenses) {
        if (e.getCategory().equalsIgnoreCase(category)) {
            total += e.getAmount();
        }
    }
    return total;
}
// Calculates the total of all expenses
public double getTotalExpenses() {
    double total = 0;

    for (Expense e : expenses) {
        total += e.getAmount();
    }
    return total;
}
 // Calculates totals for all categories
public double[] getCategoryTotals() {
    double[] totals = new double[3];

    for (Expense expense : expenses) {
        String category = expense.getCategory();
        double amount = expense.getAmount();

        if (category.equals("Food")) {
            totals[0] += amount;
        } else if (category.equals("Shopping")) {
            totals[1] += amount;
        } else if (category.equals("Transport")) {
            totals[2] += amount;
        }
    }
    return totals;
}
   // Displays category totals and the grand total
public void viewExpenses() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses have been recorded.");
        return;
    }
    double[] totals = getCategoryTotals();
    double total = 0;
    System.out.println("===== Your Expenses =====");

    for (int i = 0; i < totals.length; i++) {
        if (totals[i] > 0) {
            System.out.println(CATEGORIES[i] + " : " + totals[i] + " SAR");
        }
        total += totals[i];
    }
    System.out.println("-------------------------");
    System.out.println("Total: " + total + " SAR");
}
 // Exports category totals to a CSV file
@Override
public void exportCSV() {
    if (expenses.isEmpty()) {
        System.out.println("No expenses to export.");
        return;
    }

    double[] totals = getCategoryTotals();

    try (FileWriter file = new FileWriter("expenses.csv")) {
        file.write("Category,Amount\n");

        for (int i = 0; i < totals.length; i++) {
            if (totals[i] > 0) {
                file.write(CATEGORIES[i] + "," + totals[i] + "\n");
            }
        }
    } catch (IOException e) {
        System.out.println("Could not export the CSV report: " + e.getMessage());
        return;
    }
    System.out.println("CSV report exported successfully!");
}

//Gets category totals and creates an ExpenseChart object to display the chart window
public void showChart() {
    double[] totals = getCategoryTotals();
    ExpenseChart chart = new ExpenseChart(totals[0], totals[1], totals[2]);
    chart.showChartWindow();}
}
