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

    public Division getById(int id) {
        Division division = null;
        String query = "SELECT * FROM Division WHERE id_division = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                division = new Division();
                division.setId_division(rs.getInt("id_division"));
                division.setNombre_division(rs.getString("nombre_division"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return division;
    }

    public List<Division> getAll() {
        List<Division> divisiones = new ArrayList<>();
        String query = "SELECT * FROM Division";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Division division = new Division();
                division.setId_division(rs.getInt("id_division"));
                division.setNombre_division(rs.getString("nombre_division"));
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

            ps.setString(1, division.getNombre_division());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                inserted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }

    public boolean update(Division division) {
        boolean updated = false;
        String query = "UPDATE Division SET nombre_division = ? WHERE id_division = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, division.getNombre_division());
            ps.setInt(2, division.getId_division());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
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
}
