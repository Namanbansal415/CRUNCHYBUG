package gui;

import db.DBConnection;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class RentCarForm extends JFrame {
    public RentCarForm() {
        setTitle("Rent a Car");
        setSize(350, 300);
        setLayout(null);

        JLabel customerLabel = new JLabel("Customer:");
        JLabel carLabel = new JLabel("Car:");
        JLabel startLabel = new JLabel("Start Date (YYYY-MM-DD):");
        JLabel endLabel = new JLabel("End Date (YYYY-MM-DD):");

        JComboBox<String> customerBox = new JComboBox<>();
        JComboBox<String> carBox = new JComboBox<>();
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();

        JButton rentButton = new JButton("Rent");

        customerLabel.setBounds(20, 20, 150, 30);
        carLabel.setBounds(20, 60, 150, 30);
        startLabel.setBounds(20, 100, 200, 30);
        endLabel.setBounds(20, 140, 200, 30);

        customerBox.setBounds(180, 20, 140, 30);
        carBox.setBounds(180, 60, 140, 30);
        startDateField.setBounds(180, 100, 140, 30);
        endDateField.setBounds(180, 140, 140, 30);
        rentButton.setBounds(120, 190, 100, 30);

        add(customerLabel);
        add(carLabel);
        add(startLabel);
        add(endLabel);
        add(customerBox);
        add(carBox);
        add(startDateField);
        add(endDateField);
        add(rentButton);

        // Load data
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id, name FROM customers");
            while (rs.next()) {
                customerBox.addItem(rs.getInt("id") + " - " + rs.getString("name"));
            }

            rs = st.executeQuery("SELECT id, brand, model FROM cars WHERE available = TRUE");
            while (rs.next()) {
                carBox.addItem(rs.getInt("id") + " - " + rs.getString("brand") + " " + rs.getString("model"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }

        rentButton.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();

                int customerId = Integer.parseInt(customerBox.getSelectedItem().toString().split(" - ")[0]);
                int carId = Integer.parseInt(carBox.getSelectedItem().toString().split(" - ")[0]);
                LocalDate start = LocalDate.parse(startDateField.getText());
                LocalDate end = LocalDate.parse(endDateField.getText());

                long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);
                if (days < 1) throw new Exception("Invalid rental period.");

                // Get price
                PreparedStatement ps = con.prepareStatement("SELECT price_per_day FROM cars WHERE id = ?");
                ps.setInt(1, carId);
                ResultSet rs = ps.executeQuery();
                rs.next();
                double pricePerDay = rs.getDouble("price_per_day");
                double totalPrice = pricePerDay * days;

                // Insert rental
                ps = con.prepareStatement("INSERT INTO rentals (car_id, customer_id, start_date, end_date, total_price) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, carId);
                ps.setInt(2, customerId);
                ps.setDate(3, Date.valueOf(start));
                ps.setDate(4, Date.valueOf(end));
                ps.setDouble(5, totalPrice);
                ps.executeUpdate();

                // Update car availability
                ps = con.prepareStatement("UPDATE cars SET available = FALSE WHERE id = ?");
                ps.setInt(1, carId);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Car rented successfully!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }
}

