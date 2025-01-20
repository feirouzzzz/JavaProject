package Presentation2;

import javax.swing.*;
import java.awt.*;

public class NewJFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public NewJFrame() {
        initComponents();
    }

    private void initComponents() {
        // Initialize CardLayout and JPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add the pages to CardLayout
        cardPanel.add(new LoginPage(), "LoginPage");
        cardPanel.add(new SignUpPage(), "SignUpPage");
        cardPanel.add(new HomePage(), "HomePage");
        cardPanel.add(new GestionClients(), "GestionClientsPage");

        // Add the card panel to the content pane
        this.getContentPane().add(cardPanel);

        // Show the LoginPage by default
        cardLayout.show(cardPanel, "LoginPage");

        // Set the default window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Adjust size to your preference
        setLocationRelativeTo(null); // Center the window
    }

    // Methods to switch between pages
    public void showHomePage() {
        cardLayout.show(cardPanel, "HomePage");
    }

    public void showSignUpPage() {
        cardLayout.show(cardPanel, "SignUpPage");
    }

    public void showLoginPage() {
        cardLayout.show(cardPanel, "LoginPage");
    }

    public void showGestionClientsPage() {
        cardLayout.show(cardPanel, "GestionClientsPage");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }
}
