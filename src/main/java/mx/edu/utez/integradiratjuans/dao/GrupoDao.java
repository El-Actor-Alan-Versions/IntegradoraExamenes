package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Grupo;
import mx.edu.utez.integradiratjuans.model.Materia;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDao {

        public List<Grupo> getAll() {
            List<Grupo> grupos = new ArrayList<>();
            String query = "SELECT g.*, c.Nombre_carrera " +
                    "FROM grupo g " +
                    "JOIN carrera c ON g.id_carrera = c.id_carrera";

            try (Connection con = DatabaseConnectionManager.getConnection();
                 PreparedStatement ps = con.prepareStatement(query)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Grupo grupo = new Grupo();
                    grupo.setIdGrupo(rs.getInt("id_grupo"));
                    grupo.setGradoGrupo(rs.getString("Grado_grupo"));
                    grupo.setIdCarrera(rs.getInt("id_carrera"));
                    grupo.setNombreCarrera(rs.getString("Nombre_carrera")); // Asignar el nombre de la carrera
                    grupos.add(grupo);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return grupos;
        }

    public List<Integer> obtenerIdGruposPorMatricula(String matricula) {
        List<Integer> idGrupos = new ArrayList<>();
        String sql = "SELECT g.id_grupo FROM grupo g JOIN clase c ON c.id_grupo = g.id_grupo WHERE c.matricula = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, matricula);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idGrupos.add(resultSet.getInt("id_grupo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGrupos;
    }


        // Método para obtener el id_clase a partir del id_grupo
        public int getIdClaseByGrupoId(int idGrupo) throws SQLException {
            String sql = "SELECT id_clase FROM clase WHERE id_grupo = ?";

            try (Connection con = DatabaseConnectionManager.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, idGrupo);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt("id_clase");
                } else {
                    return -1; // No se encontró la clase
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Error al ejecutar la consulta: " + e.getMessage(), e);
            }
        }




    public boolean insert(Grupo grupo){
        boolean inserted = false;
        String query = "INSERT INTO grupo (Grado_grupo, id_carrera) VALUES (?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, grupo.getGradoGrupo());
            statement.setInt(2, grupo.getIdCarrera());
            int rowsAffected = statement.executeUpdate();

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
        String query = "UPDATE grupo SET Grado_grupo = ?, id_carrera = ? WHERE id_grupo = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, grupo.getGradoGrupo());
            ps.setInt(2, grupo.getIdCarrera());
            ps.setInt(3, grupo.getIdGrupo());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }


    public Grupo getById(int idGrupo) throws SQLException {
        String query = "SELECT * FROM grupo WHERE id_grupo = ?";
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
        String query = "DELETE FROM grupo WHERE id_grupo = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idGrupo);
            statement.executeUpdate();
        }
    }

}
