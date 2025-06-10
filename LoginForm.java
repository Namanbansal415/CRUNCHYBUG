package gui;

import db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("Car Rental Login");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background image
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/car_bg.jpg"))));
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(80, 60, 330, 200);
        panel.setBackground(new Color(0, 0, 0, 100));
        panel.setLayout(null);
        add(panel);

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(120, 10, 100, 30);
        panel.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(30, 50, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 50, 180, 25);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(30, 90, 80, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 90, 180, 25);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(60, 140, 90, 30);
        loginBtn.setFocusPainted(false);
        loginBtn.setBackground(Color.DARK_GRAY);
        loginBtn.setForeground(Color.WHITE);
        panel.add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(170, 140, 90, 30);
        registerBtn.setFocusPainted(false);
        registerBtn.setBackground(new Color(0, 102, 204));
        registerBtn.setForeground(Color.WHITE);
        panel.add(registerBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterForm().setVisible(true);
        });
    }

    private void login() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new MainMenu().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginForm().setVisible(true);
    }
}
