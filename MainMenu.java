package gui;

import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Car Rental System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton carBtn = new JButton("Add Car");
        JButton customerBtn = new JButton("Add Customer");
        JButton rentBtn = new JButton("Rent a Car");

        carBtn.setBounds(120, 50, 150, 30);
        customerBtn.setBounds(120, 100, 150, 30);
        rentBtn.setBounds(120, 150, 150, 30);

        add(carBtn);
        add(customerBtn);
        add(rentBtn);

        carBtn.addActionListener(e -> new AddCarForm().setVisible(true));
        customerBtn.addActionListener(e -> new AddCustomerForm().setVisible(true));
        rentBtn.addActionListener(e -> new RentCarForm().setVisible(true));
    }
}

