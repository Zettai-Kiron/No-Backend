import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PharmacyGUI extends JFrame {
    private Pharmacy pharmacy;

    public PharmacyGUI() {
        pharmacy = new Pharmacy();
        setTitle("Pharmacy System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Components
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Medicine Name:");
        JTextField nameField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        JButton addButton = new JButton("Add Medicine");
        JButton viewButton = new JButton("View Inventory");
        JButton sellButton = new JButton("Process Sale");

        // Add components to panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);

        // Add panel and buttons to frame
        add(panel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(sellButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                pharmacy.addMedicine(new Medicine(name, price, quantity));
                JOptionPane.showMessageDialog(null, "Medicine added successfully!");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Medicine> inventory = pharmacy.getInventory();
                StringBuilder inventoryDetails = new StringBuilder();
                for (Medicine medicine : inventory) {
                    inventoryDetails.append("Name: ").append(medicine.getName())
                            .append(", Price: ").append(medicine.getPrice())
                            .append(", Quantity: ").append(medicine.getQuantity())
                            .append("\n");
                }
                JOptionPane.showMessageDialog(null, inventoryDetails.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                boolean success = pharmacy.processSale(name, quantity);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Sale processed successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sale failed. Check inventory or quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PharmacyGUI().setVisible(true);
            }
        });
    }
}
