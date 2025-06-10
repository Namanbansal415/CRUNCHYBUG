package gui;

import db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterForm() {
        setTitle("Register User");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Register New Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBounds(90, 30, 250, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 80, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(140, 80, 180, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 120, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 120, 180, 25);
        add(passwordField);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(140, 170, 100, 30);
        add(registerBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(250, 170, 70, 30);
        add(backBtn);

        registerBtn.addActionListener(e -> registerUser());
        backBtn.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }

    private void registerUser() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "User Registered Successfully!");
            dispose();
            new LoginForm().setVisible(true);
        } catch (SQLException ex) {
            if (ex.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            } else {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new RegisterForm().setVisible(true);
    });
}

}

