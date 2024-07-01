package mx.edu.utez.integradiratjuans.dao;


import mx.edu.utez.integradiratjuans.model.Carrera;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarreraDao {

    public Carrera getById(int id) {
        Carrera carrera = null;
        String query = "SELECT * FROM Carrera WHERE id_carrera = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                carrera = new Carrera();
                carrera.setId_carrera(rs.getInt("id_carrera"));
                carrera.setNombre_carrera(rs.getString("nombre_carrera"));
                carrera.setId_division(rs.getInt("id_division"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carrera;
    }

    public List<Carrera> getAll() {
        List<Carrera> carreras = new ArrayList<>();
        String query = "SELECT * FROM Carrera";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carrera carrera = new Carrera();
                carrera.setId_carrera(rs.getInt("id_carrera"));
                carrera.setNombre_carrera(rs.getString("nombre_carrera"));
                carrera.setId_division(rs.getInt("id_division"));
                carreras.add(carrera);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carreras;
    }

    public boolean insert(Carrera carrera) {
        boolean inserted = false;
        String query = "INSERT INTO Carrera (nombre_carrera, id_division) VALUES (?, ?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, carrera.getNombre_carrera());
            ps.setInt(2, carrera.getId_division());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                inserted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }

    public boolean update(Carrera carrera) {
        boolean updated = false;
        String query = "UPDATE Carrera SET nombre_carrera = ?, id_division = ? WHERE id_carrera = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, carrera.getNombre_carrera());
            ps.setInt(2, carrera.getId_division());
            ps.setInt(3, carrera.getId_carrera());
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
        String query = "DELETE FROM Carrera WHERE id_carrera = ?";

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

