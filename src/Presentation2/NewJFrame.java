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

        // Add the card panel to the content pane
        this.getContentPane().add(cardPanel);

        // Show the LoginPage by default
        cardLayout.show(cardPanel, "LoginPage");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    public void showHomePage() {
        // Show the HomePage
        cardLayout.show(cardPanel, "HomePage");
    }

    public void showSignUpPage() {
        // Show the SignUpPage
        cardLayout.show(cardPanel, "SignUpPage");
    }

    public void showLoginPage() {
        // Show the LoginPage
        cardLayout.show(cardPanel, "LoginPage");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }
}
