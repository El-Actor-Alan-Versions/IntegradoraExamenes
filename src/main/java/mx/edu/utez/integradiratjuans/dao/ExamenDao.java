package mx.edu.utez.integradiratjuans.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;
import mx.edu.utez.integradiratjuans.model.Examen;

public class ExamenDao {

    public int insert(Examen examen) {
        String query = "INSERT INTO examen (Nombre, Fecha_aplicacion, Fecha_cierre, id_clase, descripcion, estado) VALUES (?, ?, ?, ?, ?, ?)";
        int id = -1;

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, examen.getNombre());
            pstmt.setTimestamp(2, examen.getFecha_aplicacion());
            pstmt.setTimestamp(3, examen.getFecha_cierre());
            pstmt.setInt(4, examen.getId_clase());
            pstmt.setString(5, examen.getDescripcion());
            pstmt.setBoolean(6, examen.isEstado());

            int rowsAffected = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza del error
        }

        return id;
    }

    public String obtenerNombreExamenPorId(int idExamen) throws SQLException {
        String nombreExamen = null;

        String sql = "SELECT nombre FROM examen WHERE id_examen = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idExamen);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    nombreExamen = rs.getString("nombre");
                }
            }
        }
        return nombreExamen;
    }

    public String obtenerDescripcionExamenPorId(int idExamen) {
        String descripcion = null; // Corregir el nombre de la variable a "descripcion"

        String sql = "SELECT descripcion FROM examen WHERE id_examen = ?"; // Corregir el nombre de la columna en la consulta SQL

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idExamen);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    descripcion = rs.getString("descripcion"); // Corregir el nombre de la columna en la recuperación del resultado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar las excepciones adecuadamente
        }

        return descripcion;
    }



    public List<Examen> getExamenesPorClase(int idClase) throws SQLException {
        List<Examen> examenes = new ArrayList<>();
        String sql = "SELECT * FROM examen WHERE id_clase = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idClase);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Examen examen = new Examen();
                examen.setId_examen(rs.getInt("id_examen"));
                examen.setNombre(rs.getString("Nombre"));
                examen.setFecha_aplicacion(rs.getTimestamp("Fecha_aplicacion"));
                examen.setFecha_cierre(rs.getTimestamp("Fecha_cierre"));
                examen.setId_clase(rs.getInt("id_clase"));
                examen.setDescripcion(rs.getString("descripcion")); // si la columna existe
                examenes.add(examen);
            }
        }
        return examenes;
    }
    public List<Examen> getExamenesNoRealizadosPorAlumno(int idClase, String matriculaAlumno) {
        List<Examen> examenes = new ArrayList<>();
        String query = "SELECT e.* FROM examen e " +
                "JOIN examen_alumno ea ON e.id_examen = ea.id_examen " +
                "WHERE e.id_clase = ? " +
                "AND ea.matricula_alumno = ? and ea.realizado = 0";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Asignar los parámetros al PreparedStatement
            preparedStatement.setInt(1, idClase);
            preparedStatement.setString(2, matriculaAlumno);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Examen examen = new Examen();
                    examen.setId_examen(resultSet.getInt("id_examen"));
                    examen.setNombre(resultSet.getString("nombre"));
                    examen.setDescripcion(resultSet.getString("descripcion"));
                    examen.setFecha_aplicacion(resultSet.getTimestamp("fecha_aplicacion"));
                    examen.setFecha_cierre(resultSet.getTimestamp("fecha_cierre"));
                    examen.setId_clase(resultSet.getInt("id_clase"));

                    System.out.println("Examen encontrado: " + examen.getNombre());
                    examenes.add(examen);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores según sea necesario
        }

        return examenes;
    }


    public boolean updateEstadoExamen(int idExamen) {
        boolean flag = false;
        String query = "UPDATE examen SET estado = true WHERE id_examen = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Establecer el parámetro de la consulta
            preparedStatement.setInt(1, idExamen);

            // Ejecutar la actualización
            int rowsAffected = preparedStatement.executeUpdate();

            // Verificar si alguna fila fue afectada
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Puedes manejar la excepción de una manera más apropiada en tu aplicación
        }

        return flag;
    }

    public List<Examen> getAll() throws SQLException {
        List<Examen> examenes = new ArrayList<>();
        String query = "SELECT * FROM examen";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Examen examen = new Examen();
                examen.setId_examen(rs.getInt("id_examen"));
                examen.setNombre(rs.getString("nombre"));
                examen.setFecha_aplicacion(rs.getTimestamp("fecha_aplicacion"));
                examen.setFecha_cierre(rs.getTimestamp("fecha_cierre"));
                examen.setId_clase(rs.getInt("id_clase"));
                examenes.add(examen);
            }
        }
        return examenes;
    }


}
