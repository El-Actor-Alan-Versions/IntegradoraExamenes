package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.ExamenAlumno;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExamenAlumnoDao {

    public boolean insert(ExamenAlumno examenAlumno) {
        boolean flag = false;

        String query = "INSERT INTO examen_alumno (id_examen, matricula_alumno) VALUES (?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Asignar los parámetros al PreparedStatement
            preparedStatement.setInt(1, examenAlumno.getIdExamen());
            preparedStatement.setString(2, examenAlumno.getMatriculaAlumno());

            // Ejecutar la inserción y verificar si se insertó al menos una fila
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores según sea necesario
        }

        return flag;
    }

    public boolean actualizarEstadoRealizado(int idExamen, String matriculaAlumno) {
        String sql = "UPDATE examen_alumno SET realizado = 1 WHERE id_examen = ? AND matricula_alumno = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idExamen);
            stmt.setString(2, matriculaAlumno);

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0; // Devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si ocurre un error
        }
    }


}
