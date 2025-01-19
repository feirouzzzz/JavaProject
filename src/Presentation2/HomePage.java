package Presentation2;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    public HomePage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to the Home Page", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(new Color(68, 0, 102));

        add(welcomeLabel, BorderLayout.CENTER);
    }
}
