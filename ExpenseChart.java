import java.awt.*;
import javax.swing.*; 

// Bar chart panel for Food, Shopping, and Transport totals
public class ExpenseChart extends JPanel {

// Variables to store the total expenses for each category
private double foodTotal;
private double shoppingTotal;
private double transportTotal;

 // Constructor: receives the total amount for each category
public ExpenseChart(double foodTotal, double shoppingTotal, double transportTotal) {
    this.foodTotal = foodTotal;
    this.shoppingTotal = shoppingTotal;
    this.transportTotal = transportTotal;
}

// Opens a separate window for the bar chart
public void showChartWindow() {
    JFrame frame = new JFrame("Expense Chart");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(600, 420);
    frame.add(this);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

 // Draws the bar chart inside the panel
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
 // Creates Graphics2D to draw shapes and text
    Graphics2D g2 = (Graphics2D) g;
      // Makes the chart drawing smoother
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Chart title 
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("SansSerif", Font.BOLD, 22));
    String title = "Expenses by Category";
    int titleWidth = g2.getFontMetrics().stringWidth(title);
    g2.drawString(title, (getWidth() - titleWidth) / 2, 45);
 // Stores category totals, names, and bar colors in arrays

    double[] values = {foodTotal, shoppingTotal, transportTotal};
    String[] labels = {"Food", "Shopping", "Transport"};
    Color[] colors = {
        new Color(235, 55, 55),
        new Color(0, 115, 230),
        new Color(70, 195, 75)
    };

    // Highest total used to scale bar heights proportionally
    double maxValue = 0;
    for (double v : values) {
        if (v > maxValue) {
            maxValue = v;
        }
    }
 // Sets the chart baseline and maximum bar height
    int baseY = getHeight() - 75;
    int maxBarHeight = getHeight() - 175;
    if (maxBarHeight < 50) {
        maxBarHeight = 50;
    }
 // Sets the bar width, spacing, and starting position
    int barWidth = 95;
    int gap = 75;
    int totalWidth = (barWidth * 3) + (gap * 2);
    int startX = (getWidth() - totalWidth) / 2;

    // Horizontal baseline below bars
    g2.setColor(Color.DARK_GRAY);
    g2.drawLine(40, baseY, getWidth() - 40, baseY);

// Sets the font for amounts and category names
    g2.setFont(new Font("SansSerif", Font.BOLD, 13));

// Loops through the three categories to draw each bar
    for (int i = 0; i < 3; i++) {
        int x = startX + i * (barWidth + gap);

 // Calculates the bar height based on its value
        int barHeight = 0;
        if (maxValue > 0 && values[i] > 0) {
            barHeight = (int) ((values[i] / maxValue) * maxBarHeight);
            if (barHeight < 4) {
                barHeight = 4;
            }
        }

        // Y grows downward in Swing graphics
        int y = baseY - barHeight;

        // Draw bar
        g2.setColor(colors[i]);
        g2.fillRect(x, y, barWidth, barHeight);

        // Draw amount above bar
        g2.setColor(Color.BLACK);
        String valueText = String.valueOf(values[i]);
        int valueWidth = g2.getFontMetrics().stringWidth(valueText);
        g2.drawString(valueText, x + (barWidth - valueWidth) / 2, y - 8);

        // Draw category label below baseline
        String labelText = labels[i];
        int labelWidth = g2.getFontMetrics().stringWidth(labelText);
        g2.drawString(labelText, x + (barWidth - labelWidth) / 2, baseY + 22);
    }
}
}