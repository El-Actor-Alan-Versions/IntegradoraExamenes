package mx.edu.utez.integradiratjuans.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;
import mx.edu.utez.integradiratjuans.model.Examen;

public class ExamenDao {

    public boolean insert(Examen examen) {
        String query = "INSERT INTO Examen (Nombre, Fecha_aplicacion, Fecha_cierre, id_clase) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, examen.getNombre());
            pstmt.setTimestamp(2, examen.getFecha_aplicacion());
            pstmt.setTimestamp(3, examen.getFecha_cierre());
            pstmt.setInt(4, examen.getId_clase());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public List<Examen> getAll() {
        List<Examen> examenes = new ArrayList<>();
        String query = "SELECT * FROM Examen";

        try (Connection con = DatabaseConnectionManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Examen examen = new Examen();
                examen.setId_examen(rs.getInt("id_examen"));
                examen.setNombre(rs.getString("Nombre"));
                examen.setFecha_aplicacion(rs.getTimestamp("Fecha_aplicacion"));
                examen.setFecha_cierre(rs.getTimestamp("Fecha_cierre"));
                examen.setId_clase(rs.getInt("id_clase"));
                examenes.add(examen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return examenes;
    }

}
