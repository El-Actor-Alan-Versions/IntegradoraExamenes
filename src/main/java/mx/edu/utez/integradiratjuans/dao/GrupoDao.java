package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Grupo;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoDao {

    public Grupo getById(int id) {
        Grupo grupo = null;
        String query = "SELECT * FROM Grupo WHERE id_grupo = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                grupo = new Grupo();
                grupo.setId_grupo(rs.getInt("id_grupo"));
                grupo.setGrado_grupo(rs.getString("grado_grupo"));
                grupo.setId_carrera(rs.getInt("id_carrera"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grupo;
    }

    public List<Grupo> getAll() {
        List<Grupo> grupos = new ArrayList<>();
        String query = "SELECT * FROM Grupo";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId_grupo(rs.getInt("id_grupo"));
                grupo.setGrado_grupo(rs.getString("grado_grupo"));
                grupo.setId_carrera(rs.getInt("id_carrera"));
                grupos.add(grupo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grupos;
    }

    public boolean insert(Grupo grupo) {
        boolean inserted = false;
        String query = "INSERT INTO Grupo (grado_grupo, id_carrera) VALUES (?, ?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, grupo.getGrado_grupo());
            ps.setInt(2, grupo.getId_carrera());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                inserted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }

    public boolean update(Grupo grupo) {
        boolean updated = false;
        String query = "UPDATE Grupo SET grado_grupo = ?, id_carrera = ? WHERE id_grupo = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, grupo.getGrado_grupo());
            ps.setInt(2, grupo.getId_carrera());
            ps.setInt(3, grupo.getId_grupo());
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
        String query = "DELETE FROM Grupo WHERE id_grupo = ?";

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

