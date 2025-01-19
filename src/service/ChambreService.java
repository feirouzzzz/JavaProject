package service;

import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import entities.Categorie;
import entities.Chambre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Database.Database;

public class ChambreService implements IDAO<Chambre> {
    private Connection connection;
    CategorieService cc = new CategorieService();

    public ChambreService() {
        Database database = new Database();
        this.connection = database.getConnection();
    }

    @Override
    public boolean create(Chambre o) {
        String query = "INSERT INTO chambre (telephone, categorie_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // stmt.setInt(1, o.getId());
            stmt.setString(1, o.getTelephone());
            stmt.setInt(2, o.getCategorie().getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Chambre added successfully!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to add chambre.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Chambre o) {
        String query = "UPDATE chambre SET telephone = ?, categorie_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, o.getTelephone());
            stmt.setInt(2, o.getCategorie().getId());
            stmt.setInt(3, o.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Chambre updated successfully!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to update chambre.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Chambre o) {
        String query = "DELETE FROM chambre WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, o.getId());

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Chambre deleted successfully!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to delete chambre.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Chambre findById(int id) {
        String query = "SELECT * FROM chambre WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Chambre(
                        rs.getInt("id"),
                        rs.getString("telephone"),
                        cc.findById(rs.getInt("categorie_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Chambre> findAll() {
        List<Chambre> chambres = new ArrayList<>();
        String query = "SELECT c.id, c.telephone, cat.id AS categorie_id, cat.type, cat.prix "
                + "FROM chambres c "
                + "JOIN categories cat ON c.categorie_id = cat.id";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Categorie categorie = new Categorie(
                        rs.getInt("categorie_id"),
                        rs.getString("type"),
                        rs.getString("prix"));

                Chambre chambre = new Chambre(
                        rs.getInt("id"),
                        rs.getString("telephone"),
                        categorie);

                chambres.add(chambre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chambres;
    }
}
