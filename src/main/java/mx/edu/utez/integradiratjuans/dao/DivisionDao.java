package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Division;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DivisionDao {

    public List<Division> getAll() {
        List<Division> divisiones = new ArrayList<>();
        String query = "SELECT * FROM Division";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Division division = new Division();
                division.setIdDivision(rs.getInt("id_division"));
                division.setNombreDivision(rs.getString("nombre_division"));
                divisiones.add(division);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return divisiones;
    }
}
