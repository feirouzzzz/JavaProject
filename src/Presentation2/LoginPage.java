package Presentation2;

import javax.swing.*;

import entities.Client;
import service.ClientService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoginPage extends JPanel {

    ClientService clientService = new ClientService();

    public LoginPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Left Panel for Image
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(300, 600)); // Set image space size

        // Add your image here
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./images/login.jpg"));
        if (imageIcon.getIconWidth() == -1) {
            System.out.println("Image not found, check the path.");
        } else {
            JLabel imageLabel = new JLabel(
                    new ImageIcon(imageIcon.getImage().getScaledInstance(300, 600, Image.SCALE_SMOOTH)));
            leftPanel.add(imageLabel, BorderLayout.CENTER);
        }

        add(leftPanel, BorderLayout.WEST);

        // Main Panel for the Form
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        add(rightPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Welcome Back!");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(68, 0, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        rightPanel.add(titleLabel, gbc);

        // Email Field
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        emailLabel.setForeground(new Color(68, 0, 102));
        rightPanel.add(emailLabel, gbc);

        gbc.gridy++;
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(250, 30));
        rightPanel.add(emailField, gbc);

        // Password Field
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(68, 0, 102));
        rightPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 30));
        rightPanel.add(passwordField, gbc);

        // Login Button
        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(138, 43, 226));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener((ActionEvent e) -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            // Add your login validation logic here
            if (validateLogin(email, password)) {
                // Navigate to HomePage
                ((NewJFrame) SwingUtilities.getWindowAncestor(this)).showHomePage();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        rightPanel.add(loginButton, gbc);
    }

    // Mock login validation method
    private boolean validateLogin(String email, String password) {
        // Replace with actual validation logic
        // return email.equals("feirouz@gmail.com") && password.equals("password123");
        return true;
    }
}
