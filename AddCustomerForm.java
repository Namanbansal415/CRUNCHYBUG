package gui;

import db.DBConnection;
import javax.swing.*;
import java.sql.*;

public class AddCustomerForm extends JFrame {
    public AddCustomerForm() {
        setTitle("Add Customer");
        setSize(300, 250);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel emailLabel = new JLabel("Email:");

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        JButton saveButton = new JButton("Save");

        nameLabel.setBounds(20, 20, 100, 30);
        phoneLabel.setBounds(20, 60, 100, 30);
        emailLabel.setBounds(20, 100, 100, 30);

        nameField.setBounds(120, 20, 150, 30);
        phoneField.setBounds(120, 60, 150, 30);
        emailField.setBounds(120, 100, 150, 30);
        saveButton.setBounds(90, 150, 100, 30);

        add(nameLabel);
        add(phoneLabel);
        add(emailLabel);
        add(nameField);
        add(phoneField);
        add(emailField);
        add(saveButton);

        saveButton.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();
                String sql = "INSERT INTO customers (name, phone, email) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, nameField.getText());
                ps.setString(2, phoneField.getText());
                ps.setString(3, emailField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Customer Added!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
    }
}
