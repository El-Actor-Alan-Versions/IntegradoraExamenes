package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Division;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DivisionDao {

    public List<Division> getAll() {
        List<Division> divisiones = new ArrayList<>();
        String query = "SELECT * FROM Division";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Division division = new Division();
                division.setIdDivision(rs.getInt("id_division"));
                division.setNombreDivision(rs.getString("nombre_division"));
                divisiones.add(division);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisiones;
    }
    public boolean insert(Division division) {
        boolean inserted = false;
        String query = "INSERT INTO Division (nombre_division) VALUES (?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, division.getNombreDivision());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                inserted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }
    public boolean delete(int id) {
        boolean deleted = false;
        String query = "DELETE FROM Division WHERE id_division = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                deleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    public Division getById(int idDivision) throws SQLException {
        Division division = null;
        String query = "SELECT * FROM Division WHERE id_division = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idDivision);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    division = new Division();
                    division.setIdDivision(rs.getInt("id_division"));
                    division.setNombreDivision(rs.getString("nombre_division"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return division;
    }

    public boolean update(Division division) {
        boolean updated = false;
        String query = "UPDATE Division SET nombre_division = ? WHERE id_division = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, division.getNombreDivision());
            ps.setInt(2, division.getIdDivision());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
}
