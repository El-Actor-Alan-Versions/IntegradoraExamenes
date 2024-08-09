package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Respuesta;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RespuestaDao {

    public boolean insert(Respuesta respuesta) {
        String sql = "INSERT INTO respuesta (id_pregunta, acierto, matricula_estudiante, respuesta) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, respuesta.getIdPregunta());
            stmt.setInt(2, respuesta.getAcierto());
            stmt.setString(3, respuesta.getMatriculaEstudiante());
            stmt.setString(4, respuesta.getRespuesta()); // Guardar la respuesta seleccionada
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

    // Obtener respuestas de un examen específico para un alumno
    public List<Respuesta> getRespuestasByIdExamenAndMatricula(int idExamen, String matriculaEstudiante) {
        List<Respuesta> respuestas = new ArrayList<>();
        String query = "SELECT r.id_respuesta, r.respuesta, r.id_pregunta " +
                "FROM respuesta r " +
                "JOIN pregunta p ON r.id_pregunta = p.id_pregunta " +
                "WHERE p.id_examen = ? AND r.matricula_estudiante = ?";

        System.out.println("Ejecutando consulta: " + query);

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, idExamen);
            preparedStatement.setString(2, matriculaEstudiante);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setIdRespuesta(resultSet.getInt("id_respuesta"));
                    respuesta.setRespuesta(resultSet.getString("respuesta"));
                    respuesta.setIdPregunta(resultSet.getInt("id_pregunta"));
                    respuestas.add(respuesta);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return respuestas;
    }

    public Map<Integer, Respuesta> getRespuestasPorExamenYEstudiante(int idExamen, String matriculaEstudiante) {
        Map<Integer, Respuesta> respuestasMap = new HashMap<>();
        String sql = "SELECT r.id_respuesta, r.respuesta, r.id_pregunta " +
                "FROM respuesta r " +
                "JOIN pregunta p ON r.id_pregunta = p.id_pregunta " +
                "WHERE p.id_examen = ? AND r.matricula_estudiante = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idExamen);
            stmt.setString(2, matriculaEstudiante);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Respuesta respuesta = new Respuesta();
                respuesta.setIdRespuesta(rs.getInt("id_respuesta"));
                respuesta.setIdPregunta(rs.getInt("id_pregunta"));
                respuesta.setRespuesta(rs.getString("respuesta")); // Asume que este es el campo correcto para la respuesta

                // Añade la respuesta al mapa usando el ID de la pregunta como clave
                respuestasMap.put(respuesta.getIdPregunta(), respuesta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return respuestasMap;
    }




}
