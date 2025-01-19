package Presentation2;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    public HomePage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title label
        JLabel welcomeLabel = new JLabel("Welcome to the Home Page", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(68, 0, 102));

        // Add the welcome label to the top of the panel
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns with 10px gap
        buttonPanel.setBackground(Color.WHITE);

        // Define button color
        Color buttonColor = new Color(138, 43, 226); // Violet color

        // Create the buttons
        JButton gestionClientsButton = createButton("Gestion des clients", buttonColor);
        JButton gestionChambresButton = createButton("Gestion des chambres", buttonColor);
        JButton gestionReservationsButton = createButton("Gestion des réservations", buttonColor);
        JButton anotherButton = createButton("Another Button", buttonColor); // Optional

        // Add action listener to Gestion des clients button
        gestionClientsButton.addActionListener(e -> {
            // Show GestionClientsPage when clicked
            ((NewJFrame) SwingUtilities.getWindowAncestor(this)).showGestionClientsPage();
        });

        // Add buttons to the panel
        buttonPanel.add(gestionClientsButton);
        buttonPanel.add(gestionChambresButton);
        buttonPanel.add(gestionReservationsButton);
        buttonPanel.add(anotherButton);

        // Add button panel to the center of the HomePage
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Helper method to create buttons with consistent design
    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 50));
        return button;
    }
}
