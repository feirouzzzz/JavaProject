import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GestionChambres {
    private JFrame frame;
    private JPanel panel;
    private JTextField searchField;
    private JButton addButton, updateButton, deleteButton;
    private JTable table;

    public GestionChambres() {
        frame = new JFrame("Gestion des Chambres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Create a panel with a dashed border
        panel = new JPanel(new BorderLayout());
        Color customColor = new Color(106, 27, 154); // Violet-like color

        Border dashedBorder = BorderFactory.createDashedBorder(Color.BLUE); // Use Color.blue
        panel.setBorder(dashedBorder);

        // Set background color
        panel.setBackground(new Color(245, 245, 245));

        // Title
        JLabel title = new JLabel("Gestion des Chambres", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(106, 27, 154)); // Violet color
        panel.add(title, BorderLayout.NORTH);

        // Search Bar
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setToolTipText("Search by ID, Telephone, or Categorie");
        searchPanel.add(searchField);

        JButton searchButton = new JButton("🔍");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        searchPanel.add(searchButton);
        panel.add(searchPanel, BorderLayout.CENTER);

        // Action Buttons (Add, Update, Delete)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addButton = createButton("Ajouter");
        updateButton = createButton("Modifier");
        deleteButton = createButton("Supprimer");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Table (Placeholder for data)
        String[] columns = { "ID", "Telephone", "Categorie", "Actions" };
        Object[][] data = {
                { "1", "0123456789", "Standard", "Actions" },
                { "2", "0987654321", "Deluxe", "Actions" }
        };
        table = new JTable(data, columns);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER); // Use center for better layout

        // Adding everything to the frame
        frame.add(panel);
        frame.setVisible(true);

        // Adding actions for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the function to add a room
                addRoom();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the function to update the selected room
                updateRoom();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the function to delete the selected room
                deleteRoom();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the function to search rooms
                searchRooms();
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(106, 27, 154)); // Violet color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(123, 31, 162)); // Darker shade for hover
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(106, 27, 154)); // Original color
            }
        });
        return button;
    }

    private void addRoom() {
        String id = JOptionPane.showInputDialog(this.frame, "Enter Room ID:");
        String phone = JOptionPane.showInputDialog(this.frame, "Enter Telephone:");
        String category = JOptionPane.showInputDialog(this.frame, "Enter Category:");

        // Here you would call the service method to add the room.
        // For now, just showing a success message
        JOptionPane.showMessageDialog(this.frame, "Room added successfully!");

        // After adding, you can update the table (if needed)
        updateTable();
    }

    private void updateRoom() {
        // Implement the update functionality
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) table.getValueAt(selectedRow, 0);
            String newPhone = JOptionPane.showInputDialog(this.frame, "Enter new phone for Room ID " + id);
            String newCategory = JOptionPane.showInputDialog(this.frame, "Enter new category for Room ID " + id);

            // Here you would call the service method to update the room.
            // For now, just showing a success message
            JOptionPane.showMessageDialog(this.frame, "Room updated successfully!");

            // After updating, you can refresh the table
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this.frame, "No room selected!");
        }
    }

    private void deleteRoom() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) table.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(this.frame,
                    "Are you sure you want to delete Room ID " + id + "?", "Delete Room", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                // Call the service to delete the room
                // For now, just showing a success message
                JOptionPane.showMessageDialog(this.frame, "Room deleted successfully!");

                // After deletion, update the table
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(this.frame, "No room selected!");
        }
    }

    private void searchRooms() {
        String searchText = searchField.getText();
        // Here you would filter the table based on the searchText.
        // For now, just showing a message with the search query
        JOptionPane.showMessageDialog(this.frame, "Searching for: " + searchText);
    }

    private void updateTable() {
        // Here you would update the table's data after adding, updating, or deleting
        // rooms.
        // For now, we are not updating the table with real data.
        // Example:
        String[] columns = { "ID", "Telephone", "Categorie", "Actions" };
        Object[][] data = {
                { "1", "0123456789", "Standard", "Actions" },
                { "2", "0987654321", "Deluxe", "Actions" }
        };
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GestionChambres();
            }
        });
    }
}
