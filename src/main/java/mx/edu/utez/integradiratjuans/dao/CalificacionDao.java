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
}
