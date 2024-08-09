package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Calificacion;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDao {

    public boolean insert(Calificacion calificacion) {
        String sql = "INSERT INTO calificaciones (matricula_alumno, id_examen, calificacion, fecha) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, calificacion.getMatriculaAlumno());
            statement.setInt(2, calificacion.getIdExamen());
            statement.setDouble(3, calificacion.getCalificacion());
            statement.setTimestamp(4, calificacion.getFecha());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Calificacion get(int idCalificacion) {
        String sql = "SELECT * FROM calificaciones WHERE id_calificacion = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCalificacion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Calificacion calificacion = new Calificacion();
                    calificacion.setIdCalificacion(resultSet.getInt("id_calificacion"));
                    calificacion.setMatriculaAlumno(resultSet.getString("matricula_alumno"));
                    calificacion.setIdExamen(resultSet.getInt("id_examen"));
                    calificacion.setCalificacion(resultSet.getDouble("calificacion"));
                    calificacion.setFecha(resultSet.getTimestamp("fecha"));
                    return calificacion;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Calificacion> getCalificacionesConNombreExamen(String matricula) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT c.id_calificacion, c.matricula_alumno, e.nombre, c.calificacion, c.fecha " +
                "FROM calificaciones c " +
                "JOIN examen e ON c.id_examen = e.id_examen " +
                "WHERE c.matricula_alumno = ?"; // Filtra por matrícula del alumno

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, matricula); // Establecer el valor del parámetro
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Calificacion calificacion = new Calificacion();
                    calificacion.setIdCalificacion(resultSet.getInt("id_calificacion"));
                    calificacion.setMatriculaAlumno(resultSet.getString("matricula_alumno"));
                    calificacion.setCalificacion(resultSet.getDouble("calificacion"));
                    calificacion.setFecha(resultSet.getTimestamp("fecha"));
                    calificacion.setNombreExamen(resultSet.getString("nombre")); // Obtener el nombre del examen
                    calificaciones.add(calificacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calificaciones;
    }



    public List<Calificacion> obtenerCalificacionesPorDocente(String matriculaDocente) throws SQLException {
        List<Calificacion> calificaciones = new ArrayList<>();

        String sql = "SELECT cal.* FROM calificaciones cal " +
                "JOIN alumno a ON cal.matricula_alumno = a.matricula " +
                "JOIN clase c ON c.id_grupo = a.id_grupo " +
                "WHERE c.matricula = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, matriculaDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Calificacion calificacion = new Calificacion();
                    calificacion.setIdCalificacion(rs.getInt("id_calificacion"));
                    calificacion.setMatriculaAlumno(rs.getString("matricula_alumno"));
                    calificacion.setIdExamen(rs.getInt("id_examen"));
                    calificacion.setCalificacion(rs.getInt("calificacion"));
                    calificacion.setFecha(rs.getTimestamp("fecha"));
                    calificaciones.add(calificacion);
                }
            }
        }
        return calificaciones;
    }

    public List<Calificacion> getAll() {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificaciones";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Calificacion calificacion = new Calificacion();
                calificacion.setIdCalificacion(resultSet.getInt("id_calificacion"));
                calificacion.setMatriculaAlumno(resultSet.getString("matricula_alumno"));
                calificacion.setIdExamen(resultSet.getInt("id_examen"));
                calificacion.setCalificacion(resultSet.getDouble("calificacion"));
                calificacion.setFecha(resultSet.getTimestamp("fecha"));
                calificaciones.add(calificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calificaciones;
    }

    public boolean update(Calificacion calificacion) {
        String sql = "UPDATE calificaciones SET matricula_alumno = ?, id_examen = ?, calificacion = ?, fecha = ? WHERE id_calificacion = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, calificacion.getMatriculaAlumno());
            statement.setInt(2, calificacion.getIdExamen());
            statement.setDouble(3, calificacion.getCalificacion());
            statement.setTimestamp(4, calificacion.getFecha());
            statement.setInt(5, calificacion.getIdCalificacion());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idCalificacion) {
        String sql = "DELETE FROM calificaciones WHERE id_calificacion = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCalificacion);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    // Actualiza la calificación, o inserta una nueva si no existe
    public void actualizarCalificacion(int idExamen, String matricula, int idPregunta, int idOpcion, String respuesta) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtener la conexión desde el gestor de conexiones
            connection = DatabaseConnectionManager.getConnection();

            // Primero intenta actualizar la calificación existente
            String updateSQL = "UPDATE calificaciones SET calificacion = ? WHERE id_examen = ? AND matricula_alumno = ?";
            preparedStatement = connection.prepareStatement(updateSQL);

            // Ejemplo de cálculo de la calificación (ajústalo a tu lógica)
            double calificacion = calcularCalificacion(idExamen, matricula, idPregunta, idOpcion, respuesta);
            preparedStatement.setDouble(1, calificacion);
            preparedStatement.setInt(2, idExamen);
            preparedStatement.setString(3, matricula);

            int rowsAffected = preparedStatement.executeUpdate();

            // Si no se actualizó ninguna fila, inserta una nueva calificación
            if (rowsAffected == 0) {
                String insertSQL = "INSERT INTO calificaciones (matricula_alumno, id_examen, calificacion, fecha) VALUES (?, ?, ?, NOW())";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
                    insertStatement.setString(1, matricula);
                    insertStatement.setInt(2, idExamen);
                    insertStatement.setDouble(3, calificacion);
                    insertStatement.executeUpdate();
                }
            }
        } finally {
            // Asegúrate de cerrar los recursos
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    // Método para calcular la calificación, ajusta la lógica según sea necesario
    private double calcularCalificacion(int idExamen, String matricula, int idPregunta, int idOpcion, String respuesta) {
        // Implementa la lógica para calcular la calificación
        return 10.0; // Ejemplo de calificación, cámbialo según tu lógica
    }
}
