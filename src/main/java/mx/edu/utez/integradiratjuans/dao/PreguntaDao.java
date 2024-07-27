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
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, pregunta.getPregunta());
            ps.setInt(2, pregunta.getId_examen());
            if (ps.executeUpdate() == 1) {
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
                pregunta.setId_pregunta(rs.getInt("id_pregunta"));
                pregunta.setPregunta(rs.getString("pregunta"));
                pregunta.setId_examen(rs.getInt("id_examen"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return preguntas;
    }

}