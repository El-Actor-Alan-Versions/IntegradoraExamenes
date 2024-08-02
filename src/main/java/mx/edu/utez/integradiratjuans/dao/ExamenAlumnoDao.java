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


}
