package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Preguntas;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDao {

    public boolean insertarPregunta(Preguntas pregunta) {
        String sqlPregunta = "INSERT INTO Pregunta (pregunta, id_examen, tipo_pregunta) VALUES (?, ?, ?)";
        String sqlOpcion = "INSERT INTO Opciones (opcion, id_pregunta, correcta) VALUES (?, ?, ?)";

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
        String query = "SELECT id_pregunta, pregunta, tipo_pregunta FROM Pregunta WHERE id_examen = ?";

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

    public int getLastInsertedId() {
        int id = -1;
        String query = "SELECT LAST_INSERT_ID()";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getIdExamen(int idPregunta){
        int idExamen = -1;
        String query = "SELECT id_examen FROM Pregunta WHERE idPregunta = ?()";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                idExamen = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idExamen;
    }
}
