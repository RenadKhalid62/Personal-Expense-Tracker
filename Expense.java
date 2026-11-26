// Represents one expense
public class Expense {

    // Stores the expense category and amount
    private String category;
    private double amount;

    // Constructor: initializes the expense data
    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    // Returns the expense category
    public String getCategory() {
        return category;
    }

    // Returns the expense amount
    public double getAmount() {
        return amount;
    }
}

