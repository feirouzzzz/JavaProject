
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
import entities.Client;

public class ClientService implements IDAO<Client> {
	private Connection connection;

	public ClientService() {
		Database database = new Database();
		this.connection = database.getConnection();
	}

	@Override
	public boolean create(Client o) {
		String query = "INSERT INTO clients (prenom, nom, telephone, email) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, o.getPrenom());
			stmt.setString(2, o.getNom());
			stmt.setString(3, o.getTelephone());
			stmt.setString(4, o.getEmail());

			int rowsAffected = stmt.executeUpdate();
			System.out.println("Client is added !");
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("Client is not added !");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Client o) {// query contient le msg a envoyer a la bd
		String query = "UPDATE clients SET prenom = ?, nom = ?, telephone = ?, email = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, o.getPrenom());
			stmt.setString(2, o.getNom());
			stmt.setString(3, o.getTelephone());
			stmt.setString(4, o.getEmail());
			stmt.setInt(5, o.getId());

			int rowsAffected = stmt.executeUpdate();
			System.out.println("Client is updated !");
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("Client is not updated !");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Client o) {
		String query = "DELETE FROM clients WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, o.getId());

			int rowsAffected = stmt.executeUpdate();
			System.out.println("Client is deleted !");
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.out.println("Client is not deleted !");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Client findById(int id) {
		String query = "SELECT * FROM clients WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Client(
						rs.getInt("id"),
						rs.getString("prenom"),
						rs.getString("nom"),
						rs.getString("telephone"),
						rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Return null if no client is found
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		String query = "SELECT * FROM clients";

		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				clients.add(new Client(
						rs.getInt("id"),
						rs.getString("prenom"),
						rs.getString("nom"),
						rs.getString("telephone"),
						rs.getString("email")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clients;
	}
}
