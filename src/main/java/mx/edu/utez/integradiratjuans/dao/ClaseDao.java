package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Clase;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaseDao {


    public List<Clase> getAll() {
        List<Clase> clases = new ArrayList<>();
        String query = "SELECT * FROM Clase";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Clase clase = new Clase();
                clase.setId_clase(rs.getInt("id_clase"));
                clase.setId_grupo(rs.getInt("id_grupo"));
                clase.setId_materia(rs.getInt("id_materia"));
                clase.setMatricula(rs.getString("matricula"));
                clases.add(clase);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clases;
    }

    public Clase getById(int idClase) {
        Clase clase = null;
        String query = "SELECT * FROM Clase WHERE id_clase = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idClase);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                clase = new Clase();
                clase.setId_clase(rs.getInt("id_clase"));
                clase.setId_grupo(rs.getInt("id_grupo"));
                clase.setId_materia(rs.getInt("id_materia"));
                clase.setMatricula(rs.getString("matricula"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clase;
    }

    public void insert(Clase clase) {
        String query = "INSERT INTO Clase (id_grupo, id_materia, matricula) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, clase.getId_grupo());
            ps.setInt(2, clase.getId_materia());
            ps.setString(3, clase.getMatricula());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Clase clase) {
        boolean updated = false;
        String query = "UPDATE Clase SET id_grupo = ?, id_materia = ?, matricula = ? WHERE id_clase = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, clase.getId_grupo());
            ps.setInt(2, clase.getId_materia());
            ps.setString(3, clase.getMatricula());
            ps.setInt(4, clase.getId_clase());
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
        String query = "DELETE FROM Clase WHERE id_clase = ?";

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
