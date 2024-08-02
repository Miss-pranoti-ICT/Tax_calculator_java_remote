import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeTaxCalculatorGUI extends JFrame {
    private JTextField incomeField;
    private JLabel resultLabel;

    public IncomeTaxCalculatorGUI() {
        createView();

        setTitle("Income Tax Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        JLabel incomeLabel = new JLabel("Enter your income:");
        panel.add(incomeLabel, constraints);

        constraints.gridx = 1;
        incomeField = new JTextField(10);
        panel.add(incomeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        JButton calculateButton = new JButton("Calculate Tax");
        panel.add(calculateButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        resultLabel = new JLabel("Your tax is: ");
        panel.add(resultLabel, constraints);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTax();
            }
        });
    }

    private void calculateTax() {
        String input = incomeField.getText();
        if (!isNumber(input) || Double.parseDouble(input) <= 0) {
            resultLabel.setText("Invalid input. Please enter a positive number.");
            return;
        }

        double income = Double.parseDouble(input);
        double tax = calculateTax(income);
        resultLabel.setText(String.format("Your tax is: %.2f", tax));
    }

    private boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private double calculateTax(double income) {
        double tax = 0.0;

        if (income <= 300000) {
            tax = 0;
        } else if (income <= 700000) {
            tax = (income - 300000) * 0.05;
        } else if (income <= 1000000) {
            tax = 400000 * 0.05 + (income - 700000) * 0.1;
        } else if (income <= 1200000) {
            tax = 400000 * 0.05 + 300000 * 0.1 + (income - 1000000) * 0.15;
        } else if (income <= 1500000) {
            tax = 400000 * 0.05 + 300000 * 0.1 + 200000 * 0.15 + (income - 1200000) * 0.2;
        } else {
            tax = 400000 * 0.05 + 300000 * 0.1 + 200000 * 0.15 + 300000 * 0.2 + (income - 1500000) * 0.3;
        }

        return tax;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IncomeTaxCalculatorGUI().setVisible(true);
            }
        });
    }
}
