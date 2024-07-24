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

    public List<Grupo> getGruposByGrado(int gradoId) throws SQLException {
        String query = "SELECT * FROM Grupo WHERE id_grado = ?";
        List<Grupo> grupos = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, gradoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Grupo grupo = new Grupo();
                    grupo.setIdGrupo(resultSet.getInt("id_grupo"));
                    grupo.setGradoGrupo(resultSet.getString("Grado_grupo"));
                    grupo.setIdCarrera(resultSet.getInt("id_carrera"));
                    grupos.add(grupo);
                }
            }
        }
        return grupos;
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
