import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.ClientService;
import entities.Client;

public class GestionClients {
    private ClientService clientService;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public GestionClients() {
        clientService = new ClientService(); // Initialize the service

        JFrame frame = new JFrame("Gestion des Clients");
        frame.setLayout(new BorderLayout());

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
        frame.add(titlePanel, BorderLayout.NORTH);

        // Search bar
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setToolTipText("Search by name or ID");
        searchPanel.add(searchField);

        JButton searchButton = new JButton("🔍");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement search logic here, for example by name or ID
                String searchText = searchField.getText();
                if (!searchText.isEmpty()) {
                    // Simulate search
                    JOptionPane.showMessageDialog(frame, "Searching for: " + searchText);
                }
            }
        });
        searchPanel.add(searchButton);
        frame.add(searchPanel, BorderLayout.CENTER);

        // Action Buttons (Add, Update, Delete)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton ajouterBtn = createStyledButton("Ajouter", customColor, hoverColor);
        JButton modifierBtn = createStyledButton("Modifier", customColor, hoverColor);
        JButton supprimerBtn = createStyledButton("Supprimer", customColor, hoverColor);

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Table to display clients
        String[] columns = { "ID", "Prenom", "Nom", "Telephone", "Email" };
        tableModel = new DefaultTableModel(columns, 0); // Empty data at start
        clientTable = new JTable(tableModel);
        clientTable.setFont(new Font("Arial", Font.PLAIN, 14));
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(clientTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Actions for buttons
        ajouterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to add a new client
                String prenom = JOptionPane.showInputDialog("Enter prenom:");
                String nom = JOptionPane.showInputDialog("Enter nom:");
                String telephone = JOptionPane.showInputDialog("Enter telephone:");
                String email = JOptionPane.showInputDialog("Enter email:");

                Client client = new Client(0, prenom, nom, telephone, email); // ID will be auto-generated
                boolean isCreated = clientService.create(client);
                if (isCreated) {
                    JOptionPane.showMessageDialog(frame, "Client added successfully!");
                    refreshTable(); // Refresh the table after adding a client
                }
            }
        });

        modifierBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to modify an existing client
                int selectedRow = clientTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) clientTable.getValueAt(selectedRow, 0);
                    Client client = clientService.findById(id);

                    if (client != null) {
                        String newPrenom = JOptionPane.showInputDialog("Enter new prenom:", client.getPrenom());
                        String newNom = JOptionPane.showInputDialog("Enter new nom:", client.getNom());
                        String newTelephone = JOptionPane.showInputDialog("Enter new telephone:",
                                client.getTelephone());
                        String newEmail = JOptionPane.showInputDialog("Enter new email:", client.getEmail());

                        client.setPrenom(newPrenom);
                        client.setNom(newNom);
                        client.setTelephone(newTelephone);
                        client.setEmail(newEmail);

                        boolean isUpdated = clientService.update(client);
                        if (isUpdated) {
                            JOptionPane.showMessageDialog(frame, "Client updated successfully!");
                            refreshTable(); // Refresh the table after modifying a client
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Client not found!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a client to modify!");
                }
            }
        });

        supprimerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to delete a client
                int selectedRow = clientTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) clientTable.getValueAt(selectedRow, 0);
                    Client client = clientService.findById(id);

                    if (client != null) {
                        boolean isDeleted = clientService.delete(client);
                        if (isDeleted) {
                            JOptionPane.showMessageDialog(frame, "Client deleted successfully!");
                            refreshTable(); // Refresh the table after deleting a client
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Client not found!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a client to delete!");
                }
            }
        });

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color buttonColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(buttonColor); // Violet color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));

        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hoverColor); // Darker shade for hover
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(buttonColor); // Original color
            }
        });

        return button;
    }

    // Method to refresh the table after performing any action
    private void refreshTable() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Fetch updated list of clients and add them to the table
        for (Client client : clientService.findAll()) {
            tableModel.addRow(new Object[] { client.getId(), client.getPrenom(), client.getNom(), client.getTelephone(),
                    client.getEmail() });
        }
    }

    public static void main(String[] args) {
        new GestionClients(); // Run the application
    }
}
