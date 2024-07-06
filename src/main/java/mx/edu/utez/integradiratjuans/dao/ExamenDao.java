package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Examen;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamenDao {
    // Otros métodos ...

    public boolean insert(Examen examen) {
        String query = "INSERT INTO Examen (nombre, fecha_aplicacion, fecha_cierre, id_clase) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, examen.getNombre());
            ps.setDate(2, new java.sql.Date(examen.getFecha_aplicacion().getTime()));
            ps.setDate(3, new java.sql.Date(examen.getFecha_cierre().getTime()));
            ps.setInt(4, examen.getId_clase());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
