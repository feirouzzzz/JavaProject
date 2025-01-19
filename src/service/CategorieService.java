package service;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.List;

import dao.IDAO;
import Database.Database;
import entities.Categorie;

public class CategorieService implements IDAO<Categorie> {
	private Connection connection;

    public CategorieService() {
        Database database = new Database(); 
        this.connection = database.getConnection();
    }




        @Override
        public boolean create(Categorie o) {
            String query = "INSERT INTO categorie (id, code, libelle) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, o.getId());
                stmt.setString(2, o.getCode());
                stmt.setString(3, o.getLibelle());

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Categorie added successfully!");
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.out.println("Failed to add categorie.");
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean update(Categorie o) {
            String query = "UPDATE categorie SET code = ?, libelle = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, o.getCode());
                stmt.setString(2, o.getLibelle());
                stmt.setInt(3, o.getId());

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Categorie updated successfully!");
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.out.println("Failed to update categorie.");
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean delete(Categorie o) {
            String query = "DELETE FROM categorie WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, o.getId());

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Categorie deleted successfully!");
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.out.println("Failed to delete categorie.");
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public Categorie findById(int id) {
            String query = "SELECT * FROM categorie WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new Categorie(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("libelle")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public List<Categorie> findAll() {
            List<Categorie> categories = new ArrayList<>();
            String query = "SELECT * FROM categorie";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    categories.add(new Categorie(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("libelle")
                    ));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return categories;
        }
    }
