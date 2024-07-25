package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Grupo;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDao {

    public List<Grupo> getAll() throws SQLException {
        String query = "SELECT * FROM Grupo";
        List<Grupo> grupos = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Grupo grupo = new Grupo();
                grupo.setIdGrupo(resultSet.getInt("id_grupo"));
                grupo.setGradoGrupo(resultSet.getString("Grado_grupo"));
                grupo.setIdCarrera(resultSet.getInt("id_carrera"));
                grupos.add(grupo);
            }
        }
        return grupos;
    }

    public void insert(Grupo grupo) throws SQLException {
        String query = "INSERT INTO Grupo (Grado_grupo, id_carrera) VALUES (?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, grupo.getGradoGrupo());
            statement.setInt(2, grupo.getIdCarrera());
            statement.executeUpdate();
        }
    }

    public Grupo getById(int idGrupo) throws SQLException {
        String query = "SELECT * FROM Grupo WHERE id_grupo = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idGrupo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Grupo(
                            rs.getInt("id_grupo"),
                            rs.getString("grado_grupo"),
                            rs.getInt("id_carrera")
                    );
                }
            }
        }
        return null;
    }


    public void delete(int idGrupo) throws SQLException {
        String query = "DELETE FROM Grupo WHERE id_grupo = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idGrupo);
            statement.executeUpdate();
        }
    }

}
