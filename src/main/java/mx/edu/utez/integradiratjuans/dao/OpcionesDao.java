package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpcionesDao {
    public boolean insert(Opcion opcion) {
        boolean flag = false;
        String query = "INSERT INTO Opciones (opcion, id_pregunta, correcta) VALUES (?,?,?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, opcion.getOpcion());
            ps.setInt(2, opcion.getIdPregunta());
            ps.setBoolean(3, opcion.isCorrecta());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Opcion> getAll() {
        List<Opcion> opciones = new ArrayList<>();
        String query = "SELECT * FROM Opciones";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Opcion opcion = new Opcion();
                opcion.setIdOpcion(rs.getInt("id_opcion"));
                opcion.setOpcion(rs.getString("opcion"));
                opcion.setIdPregunta(rs.getInt("id_pregunta"));
                opcion.setCorrecta(rs.getBoolean("correcta"));
                opciones.add(opcion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return opciones;
    }
}
