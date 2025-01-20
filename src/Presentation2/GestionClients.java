package Presentation2;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import service.ClientService;
import entities.Client;

public class GestionClients extends JPanel {

    private ClientService clientService;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public GestionClients() {
        clientService = new ClientService(); // Initialize the service

        setLayout(new BorderLayout());

        // Create custom color
        Color customColor = new Color(106, 27, 154); // Violet-like color
        Color hoverColor = new Color(123, 31, 162); // Darker shade for hover

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Gestion des Clients");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(customColor);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Search bar
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setToolTipText("Search by name or ID");
        searchPanel.add(searchField);

        JButton searchButton = new JButton("🔍");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            if (!searchText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Searching for: " + searchText);
            }
        });
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.CENTER);

        // Action Buttons (Add, Update, Delete)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton ajouterBtn = createStyledButton("Ajouter", customColor, hoverColor);
        JButton modifierBtn = createStyledButton("Modifier", customColor, hoverColor);
        JButton supprimerBtn = createStyledButton("Supprimer", customColor, hoverColor);

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Table to display clients
        String[] columns = { "ID", "Prenom", "Nom", "Telephone", "Email" };
        tableModel = new DefaultTableModel(columns, 0); // Empty data at start
        clientTable = new JTable(tableModel);
        clientTable.setFont(new Font("Arial", Font.PLAIN, 14));
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add a button to return to HomePage
        JButton returnHomeButton = new JButton("Return to Home Page");
        returnHomeButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnHomeButton.setBackground(customColor);
        returnHomeButton.setForeground(Color.WHITE);
        returnHomeButton.setFocusPainted(false);
        returnHomeButton.addActionListener(e -> {
            // Call the method from NewJFrame to switch to the HomePage
            ((NewJFrame) SwingUtilities.getWindowAncestor(this)).showHomePage();
        });

        JPanel returnPanel = new JPanel();
        returnPanel.add(returnHomeButton);
        add(returnPanel, BorderLayout.NORTH); // Add the button at the top

        setSize(800, 600);
        setVisible(true);

        // Refresh the table with clients' data
        refreshTable();
    }

    private JButton createStyledButton(String text, Color buttonColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonColor);
            }
        });
        return button;
    }

    // Helper method to refresh the table data with clients from ClientService
    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows

        // Get the list of clients from the service
        for (Client client : clientService.findAll()) {
            Object[] row = { client.getId(), client.getPrenom(), client.getNom(), client.getTelephone(),
                    client.getEmail() };
            tableModel.addRow(row); // Add a row to the table
        }
    }
}
