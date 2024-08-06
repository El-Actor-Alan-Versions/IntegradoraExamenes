package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Respuesta;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RespuestaDao {

    public boolean insert(Respuesta respuesta) {
        String sql = "INSERT INTO respuesta (id_pregunta, acierto, matricula_estudiante) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, respuesta.getIdPregunta());
            stmt.setInt(2, respuesta.getAcierto());
            stmt.setString(3, respuesta.getMatriculaEstudiante()); // Establece el valor de matrícula
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<String> CompararRespuestas(int idPregunta) {
        List<String> opcionesCorrectas = new ArrayList<>();
        String sql = "SELECT opcion FROM opciones WHERE id_pregunta = ? AND correcta = 1;";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                opcionesCorrectas.add(rs.getString("opcion")); // Corregido para obtener la columna "opcion"
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opcionesCorrectas;
    }

}
