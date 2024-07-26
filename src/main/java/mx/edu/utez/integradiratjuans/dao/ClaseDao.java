package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Clase;
import mx.edu.utez.integradiratjuans.model.Grupo;
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

    public Clase getById(int idClase) throws SQLException {
        String query = "SELECT * FROM Clase WHERE id_clase = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idClase);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Clase(
                            rs.getInt("id_clase"),
                            rs.getInt("id_grupo"),
                            rs.getInt("id_materia"),
                            rs.getString("matricula")
                    );
                }
            }
        }
        return null;
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


    public List<Clase> getClasesByDocente(String matricula) throws SQLException {
        List<Clase> clases = new ArrayList<>();
        String sql = "SELECT Clase.id_clase, Grupo.Grado_grupo " +
                "FROM Clase " +
                "INNER JOIN Grupo ON Clase.id_grupo = Grupo.id_grupo " +
                "WHERE Clase.matricula = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Clase clase = new Clase();
                clase.setId_clase(rs.getInt("id_clase"));
                clase.setGradoGrupo(rs.getString("Grado_grupo"));
                clases.add(clase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al ejecutar la consulta: " + e.getMessage(), e);
        }

        return clases;
    }


    public String getGrupoNameByIdClase(int idClase) {
        String grupoName = null;
        String query = "SELECT g.Grado_grupo FROM Clase c JOIN Grupo g ON c.id_grupo = g.id_grupo WHERE c.id_clase = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, idClase);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    grupoName = rs.getString("Grado_grupo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupoName;
    }

}




