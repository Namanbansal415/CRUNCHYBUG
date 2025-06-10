package gui;

import db.DBConnection;
import javax.swing.*;
import java.sql.*;

public class AddCarForm extends JFrame {
    public AddCarForm() {
        setTitle("Add New Car");
        setSize(300, 250);
        setLayout(null);

        JLabel brandLabel = new JLabel("Brand:");
        JLabel modelLabel = new JLabel("Model:");
        JLabel priceLabel = new JLabel("Price/Day:");

        JTextField brandField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField priceField = new JTextField();

        JButton saveButton = new JButton("Save");

        brandLabel.setBounds(20, 20, 100, 30);
        modelLabel.setBounds(20, 60, 100, 30);
        priceLabel.setBounds(20, 100, 100, 30);

        brandField.setBounds(120, 20, 150, 30);
        modelField.setBounds(120, 60, 150, 30);
        priceField.setBounds(120, 100, 150, 30);
        saveButton.setBounds(90, 150, 100, 30);

        add(brandLabel);
        add(modelLabel);
        add(priceLabel);
        add(brandField);
        add(modelField);
        add(priceField);
        add(saveButton);

        saveButton.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO cars (brand, model, price_per_day) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, brandField.getText());
                ps.setString(2, modelField.getText());
                ps.setDouble(3, Double.parseDouble(priceField.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Car Added!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }
}

