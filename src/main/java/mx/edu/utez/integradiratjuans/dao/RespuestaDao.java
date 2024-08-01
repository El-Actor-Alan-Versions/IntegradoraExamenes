package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Preguntas;
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
        boolean flag = false;
        String query = "INSERT INTO Respuesta (acierto, id_pregunta) VALUES (?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, respuesta.getAcierto());
            ps.setInt(2, respuesta.getIdPregunta());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Preguntas obtenerPreguntaPorId(int idPregunta) {
        Preguntas pregunta = null;
        String sql = "SELECT id_pregunta, pregunta, tipo_pregunta FROM Pregunta WHERE id_pregunta = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pregunta = new Preguntas();
                pregunta.setIdPregunta(rs.getInt("id_pregunta"));
                pregunta.setTexto(rs.getString("pregunta"));
                pregunta.setTipo(rs.getString("tipo_pregunta"));
                // Si hay más atributos, también deberías configurarlos aquí
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pregunta;
    }


    public List<String> CompararRespuestas(int idPregunta) {
        List<String> opcionesCorrectas = new ArrayList<>();
        String sql = "SELECT id_opcion FROM opciones WHERE id_pregunta = ? AND correcta = 1";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                opcionesCorrectas.add(rs.getString("id_opcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return opcionesCorrectas;
    }

}
