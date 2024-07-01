package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Materia;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDao {

    public Materia getById(int id) {
        Materia materia = null;
        String query = "SELECT * FROM Materia WHERE id_materia = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre_materia(rs.getString("nombre_materia"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materia;
    }

    public List<Materia> getAll() {
        List<Materia> materias = new ArrayList<>();
        String query = "SELECT * FROM Materia";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre_materia(rs.getString("nombre_materia"));
                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }


    public boolean insert(Materia materia) {
        boolean inserted = false;
        String query = "INSERT INTO Materia (nombre_materia) VALUES (?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, materia.getNombre_materia());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                inserted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }

    public boolean update(Materia materia) {
        boolean updated = false;
        String query = "UPDATE Materia SET nombre_materia = ? WHERE id_materia = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, materia.getNombre_materia());
            ps.setInt(2, materia.getId_materia());
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
        String query = "DELETE FROM Materia WHERE id_materia = ?";

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

