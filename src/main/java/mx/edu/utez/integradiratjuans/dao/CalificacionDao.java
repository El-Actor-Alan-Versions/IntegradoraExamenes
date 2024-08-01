package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Calificacion;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDao {
    private Connection connection;



    public void guardarCalificacion(Calificacion calificacion) throws SQLException {
        String sql = "INSERT INTO Calificaciones (matricula_alumno, id_examen, calificacion, fecha) VALUES (?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, calificacion.getMatriculaAlumno());
            statement.setInt(2, calificacion.getIdExamen());
            statement.setDouble(3, calificacion.getCalificacion());
            statement.executeUpdate();
        }
    }

    // Método para insertar una calificación
    public void insertarCalificacion(Calificacion calificacion) throws SQLException {
        String query = "INSERT INTO Calificaciones (matricula_alumno, id_examen, calificacion, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, calificacion.getMatriculaAlumno());
            stmt.setInt(2, calificacion.getIdExamen());
            stmt.setDouble(3, calificacion.getCalificacion());
            stmt.setTimestamp(4, calificacion.getFecha());
            stmt.executeUpdate();
        }
    }

    // Método para obtener una calificación por ID
    public Calificacion obtenerCalificacionPorId(int idCalificacion) throws SQLException {
        String query = "SELECT * FROM Calificaciones WHERE id_calificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCalificacion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Calificacion(
                            rs.getInt("id_calificacion"),
                            rs.getString("matricula_alumno"),
                            rs.getInt("id_examen"),
                            rs.getDouble("calificacion"),
                            rs.getTimestamp("fecha")
                    );
                }
            }
        }
        return null;
    }

    // Método para obtener todas las calificaciones para un examen específico
    public List<Calificacion> obtenerCalificacionesPorExamen(int idExamen) throws SQLException {
        List<Calificacion> calificaciones = new ArrayList<>();
        String query = "SELECT * FROM Calificaciones WHERE id_examen = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idExamen);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    calificaciones.add(new Calificacion(
                            rs.getInt("id_calificacion"),
                            rs.getString("matricula_alumno"),
                            rs.getInt("id_examen"),
                            rs.getDouble("calificacion"),
                            rs.getTimestamp("fecha")
                    ));
                }
            }
        }
        return calificaciones;
    }

    // Método para actualizar una calificación
    public void actualizarCalificacion(Calificacion calificacion) throws SQLException {
        String query = "UPDATE Calificaciones SET matricula_alumno = ?, id_examen = ?, calificacion = ?, fecha = ? WHERE id_calificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, calificacion.getMatriculaAlumno());
            stmt.setInt(2, calificacion.getIdExamen());
            stmt.setDouble(3, calificacion.getCalificacion());
            stmt.setTimestamp(4, calificacion.getFecha());
            stmt.setInt(5, calificacion.getIdCalificacion());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar una calificación por ID
    public void eliminarCalificacion(int idCalificacion) throws SQLException {
        String query = "DELETE FROM Calificaciones WHERE id_calificacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCalificacion);
            stmt.executeUpdate();
        }
    }
}
