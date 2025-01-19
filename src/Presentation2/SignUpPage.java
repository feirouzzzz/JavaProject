package Presentation2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpPage extends JPanel {

    public SignUpPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel titleLabel = new JLabel("Create Your Account");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(68, 0, 102));
        mainPanel.add(titleLabel, gbc);

        // Email Field
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        emailLabel.setForeground(new Color(68, 0, 102));
        mainPanel.add(emailLabel, gbc);

        gbc.gridy++;
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, 35));
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        mainPanel.add(emailField, gbc);

        // Password Field
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(68, 0, 102));
        mainPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 35));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        mainPanel.add(passwordField, gbc);

        // Sign Up Button
        gbc.gridy++;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setPreferredSize(new Dimension(300, 40));
        signUpButton.setBackground(new Color(68, 0, 102));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        signUpButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Account Created Successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        mainPanel.add(signUpButton, gbc);

        // Login Link
        gbc.gridy++;
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginPanel.setBackground(Color.WHITE);
        JLabel loginLabel = new JLabel("Already have an account?");
        loginLabel.setForeground(Color.GRAY);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(68, 0, 102));
        loginButton.setBorderPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.addActionListener((ActionEvent e) -> {
            ((NewJFrame) SwingUtilities.getWindowAncestor(this)).showLoginPage();
        });

        loginPanel.add(loginLabel);
        loginPanel.add(loginButton);
        mainPanel.add(loginPanel, gbc);
    }
}
