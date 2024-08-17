package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Preguntas;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDao {

    public boolean insertarPregunta(Preguntas pregunta) {
        String sqlPregunta = "INSERT INTO pregunta (pregunta, id_examen, tipo_pregunta) VALUES (?, ?, ?)";
        String sqlOpcion = "INSERT INTO opciones (opcion, id_pregunta, correcta) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmtPregunta = conn.prepareStatement(sqlPregunta, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmtOpcion = conn.prepareStatement(sqlOpcion)) {

            // Insertar la pregunta
            pstmtPregunta.setString(1, pregunta.getTexto());
            pstmtPregunta.setInt(2, pregunta.getIdExamen());
            pstmtPregunta.setString(3, pregunta.getTipo());
            int affectedRows = pstmtPregunta.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            // Obtener el ID generado para la pregunta
            try (var generatedKeys = pstmtPregunta.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idPregunta = generatedKeys.getInt(1);
                    pregunta.setIdPregunta(idPregunta);

                    // Insertar las opciones
                    for (Opcion opcion : pregunta.getOpciones()) {
                        pstmtOpcion.setString(1, opcion.getOpcion());
                        pstmtOpcion.setInt(2, idPregunta);
                        pstmtOpcion.setBoolean(3, opcion.isCorrecta());
                        pstmtOpcion.addBatch();
                    }
                    pstmtOpcion.executeBatch();
                }
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Preguntas> obtenerPreguntasPorExamen(int idExamen) {
        List<Preguntas> preguntas = new ArrayList<>();
        String query = "SELECT id_pregunta, pregunta, tipo_pregunta FROM pregunta WHERE id_examen = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idExamen);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Preguntas pregunta = new Preguntas();
                pregunta.setIdPregunta(rs.getInt("id_pregunta"));
                pregunta.setTexto(rs.getString("pregunta"));
                pregunta.setTipo(rs.getString("tipo_pregunta"));
                preguntas.add(pregunta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preguntas;
    }

    public String getRespuestaCorrecta(int idPregunta) throws SQLException {
        String sql = "SELECT respuesta_correcta FROM pregunta WHERE id_pregunta = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("respuesta_correcta");
            }
        }
        return null;
    }

    public List<String> getRespuestasCorrectas(int idPregunta) throws SQLException {
        List<String> respuestasCorrectas = new ArrayList<>();
        String sql = "SELECT id_opcion FROM opcionesCorrectas WHERE id_pregunta = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                respuestasCorrectas.add(rs.getString("id_opcion"));
            }
        }
        return respuestasCorrectas;
    }

    public double getPuntajePregunta(int idPregunta) throws SQLException {
        String sql = "SELECT puntaje FROM pregunta WHERE id_pregunta = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("puntaje");
            }
        }
        return 0;
    }

    // Método para obtener preguntas por ID de examen
    public List<Preguntas> getPreguntasByIdExamen(int idExamen) {
        List<Preguntas> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM pregunta WHERE id_examen = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idExamen);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Preguntas pregunta = new Preguntas();
                    pregunta.setIdPregunta(rs.getInt("id_pregunta"));
                    pregunta.setTexto(rs.getString("pregunta"));
                    pregunta.setIdExamen(rs.getInt("id_examen"));
                    // Asigna otros campos según el esquema de tu tabla Pregunta
                    preguntas.add(pregunta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preguntas;
    }

    public List<Preguntas> getPreguntasPorExamen(int examenId) {
        List<Preguntas> preguntas = new ArrayList<>();
        String query = "SELECT id_pregunta, pregunta, tipo_pregunta FROM pregunta WHERE id_examen = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, examenId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Preguntas pregunta = new Preguntas();
                pregunta.setIdPregunta(rs.getInt("id_pregunta"));
                pregunta.setTexto(rs.getString("pregunta"));
                pregunta.setTipo(rs.getString("tipo_pregunta"));
                // Asignar el ID del examen a la pregunta
                pregunta.setIdExamen(examenId);
                preguntas.add(pregunta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preguntas;
    }
}
