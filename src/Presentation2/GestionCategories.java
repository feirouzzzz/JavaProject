package Presentation2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entities.Categorie;
import service.CategorieService;

public class GestionCategories extends JFrame {

    private CategorieService categorieService;

    private JTextField tfId;
    private JTextField tfCode;
    private JTextField tfLibelle;
    private JTable tableCategories;

    public GestionCategories() {
        categorieService = new CategorieService();

        // Configurer la fenêtre principale
        setTitle("Gestion des catégories");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Créer les composants de l'interface
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel labelId = new JLabel("ID:");
        tfId = new JTextField();
        panel.add(labelId);
        panel.add(tfId);

        JLabel labelCode = new JLabel("Code:");
        tfCode = new JTextField();
        panel.add(labelCode);
        panel.add(tfCode);

        JLabel labelLibelle = new JLabel("Libellé:");
        tfLibelle = new JTextField();
        panel.add(labelLibelle);
        panel.add(tfLibelle);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        panel.add(btnAjouter);
        panel.add(btnModifier);
        panel.add(btnSupprimer);

        add(panel, BorderLayout.NORTH);

        // Table des catégories
        tableCategories = new JTable();
        updateTable();
        JScrollPane scrollPane = new JScrollPane(tableCategories);
        add(scrollPane, BorderLayout.CENTER);

        // Ajouter des actions aux boutons
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ajouterCategorie();
            }
        });

        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierCategorie();
            }
        });

        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supprimerCategorie();
            }
        });
    }

    // Méthode pour ajouter une catégorie
    private void ajouterCategorie() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String code = tfCode.getText();
            String libelle = tfLibelle.getText();
            Categorie categorie = new Categorie(id, code, libelle);
            if (categorieService.create(categorie)) {
                JOptionPane.showMessageDialog(this, "Catégorie ajoutée avec succès !");
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de l'ajout de la catégorie.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID doit être un nombre entier.");
        }
    }

    // Méthode pour modifier une catégorie
    private void modifierCategorie() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String code = tfCode.getText();
            String libelle = tfLibelle.getText();
            Categorie categorie = new Categorie(id, code, libelle);
            if (categorieService.update(categorie)) {
                JOptionPane.showMessageDialog(this, "Catégorie mise à jour avec succès !");
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la mise à jour de la catégorie.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID doit être un nombre entier.");
        }
    }

    // Méthode pour supprimer une catégorie
    private void supprimerCategorie() {
        try {
            int id = Integer.parseInt(tfId.getText());
            Categorie categorie = new Categorie(id, "", "");
            if (categorieService.delete(categorie)) {
                JOptionPane.showMessageDialog(this, "Catégorie supprimée avec succès !");
                updateTable();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la suppression de la catégorie.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "L'ID doit être un nombre entier.");
        }
    }

    // Mettre à jour la table avec les catégories
    private void updateTable() {
        List<Categorie> categories = categorieService.findAll();
        String[] columnNames = { "ID", "Code", "Libellé" };
        Object[][] data = new Object[categories.size()][3];

        for (int i = 0; i < categories.size(); i++) {
            Categorie categorie = categories.get(i);
            data[i][0] = categorie.getId();
            data[i][1] = categorie.getCode();
            data[i][2] = categorie.getLibelle();
        }

        tableCategories.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionCategories().setVisible(true);
            }
        });
    }
}
