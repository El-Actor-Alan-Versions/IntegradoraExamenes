package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Pregunta;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDao {

    public boolean insert(Pregunta pregunta) {
        boolean flag = false;
        String query = "INSERT INTO Pregunta (pregunta, id_examen) VALUES (?,?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pregunta.getPregunta());
            ps.setInt(2, pregunta.getIdExamen());
            if (ps.executeUpdate() == 1) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pregunta.setIdPregunta(generatedKeys.getInt(1));
                    }
                }
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Pregunta> getAll() {
        List<Pregunta> preguntas = new ArrayList<>();
        String query = "SELECT * FROM Pregunta";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pregunta pregunta = new Pregunta();
                pregunta.setIdPregunta(rs.getInt("id_pregunta"));
                pregunta.setPregunta(rs.getString("pregunta"));
                pregunta.setIdExamen(rs.getInt("id_examen"));
                preguntas.add(pregunta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
