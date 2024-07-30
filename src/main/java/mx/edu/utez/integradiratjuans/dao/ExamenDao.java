package mx.edu.utez.integradiratjuans.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;
import mx.edu.utez.integradiratjuans.model.Examen;

public class ExamenDao {

    public int insert(Examen examen) {
        String query = "INSERT INTO Examen (Nombre, Fecha_aplicacion, Fecha_cierre, id_clase, descripcion) VALUES (?, ?, ?, ?, ?)";
        int id = -1;

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, examen.getNombre());
            pstmt.setTimestamp(2, examen.getFecha_aplicacion());
            pstmt.setTimestamp(3, examen.getFecha_cierre());
            pstmt.setInt(4, examen.getId_clase());
            pstmt.setString(5, examen.getDescripcion());

            int rowsAffected = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza del error
        }

        return id;
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
