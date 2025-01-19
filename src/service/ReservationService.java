package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import Database.Database;
import entities.Reservation;
import entities.Client;
import entities.Chambre;

public class ReservationService implements IDAO<Reservation> {
    private Connection connection;
    CategorieService categorieservice = new CategorieService();

    // Constructor to initialize the database connection
    public ReservationService() {
        Database database = new Database();
        this.connection = database.getConnection();
    }

    @Override
    public boolean create(Reservation o) {
        String query = "INSERT INTO reservation (date_debut, date_fin, client_id, chambre_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date(o.getDatedebut().getTime()));
            ps.setDate(2, new java.sql.Date(o.getDatefin().getTime()));
            ps.setInt(3, o.getClient().getId());
            ps.setInt(4, o.getChambre().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reservation o) {
        String query = "UPDATE reservation SET date_debut = ?, date_fin = ?, client_id = ?, chambre_id = ? WHERE client_id = ? and chambre_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date(o.getDatedebut().getTime()));
            ps.setDate(2, new java.sql.Date(o.getDatefin().getTime()));
            ps.setInt(3, o.getClient().getId());
            ps.setInt(4, o.getChambre().getId());
            ps.setInt(5, o.getClient().getId());
            ps.setInt(6, o.getChambre().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reservation o) {
        String query = "DELETE FROM reservation WHERE client_id = ? and chambre_id = ? ";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, o.getClient().getId());           
            ps.setInt(2, o.getChambre().getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reservation findById(int id) {
        String query = "SELECT * FROM reservation WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Client client = new ClientService().findById(rs.getInt("client_id"));   
                Chambre chambre = new ChambreService().findById(rs.getInt("chambre_id"));
                return new Reservation(
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin"),
                    client,
                    chambre
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Client client = new ClientService().findById(rs.getInt("client_id"));
                Chambre chambre = new ChambreService().findById(rs.getInt("chambre_id"));
                reservations.add(new Reservation(
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin"),
                    client,
                    chambre
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    
    
    
    public List<Chambre> findChambreBetweenDates(Client client, Date dateDebut, Date dateFin) {
        List<Chambre> chambres = new ArrayList<>();
        String query = "SELECT c.* FROM chambre c " +
                   "JOIN reservation r ON c.id = r.chambre_id " +
                   "WHERE r.client_id = ? AND " +
                   "((r.date_debut BETWEEN ? AND ?) OR (r.date_fin BETWEEN ? AND ?) OR (r.date_debut <= ? AND r.date_fin >= ?))";
    
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, client.getId());
            stmt.setDate(2, dateDebut);
            stmt.setDate(3, dateFin);
            stmt.setDate(4, dateDebut);
            stmt.setDate(5, dateFin);
            stmt.setDate(6, dateDebut);
            stmt.setDate(7, dateFin);
    
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Chambre chambre = new Chambre(
                    rs.getInt("id"),
                    rs.getString("telephone"),
                    categorieservice.findById(rs.getInt("categorie_id"))
                );
                chambres.add(chambre);
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch chambres between dates!");
            e.printStackTrace();
        }
    
        return chambres;
    }
}
