package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Opciones;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpcionesDao {
    public boolean insert (Opciones opciones) {
        boolean flag = false;
        String query = "INSERT INTO  opciones (Opcion, id_pregunta) values (?,?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1,opciones.getOpcion());
            ps.setInt(2,opciones.getId_pregunta());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public  List<Opciones> getAll(){
        List<Opciones> opciones = new ArrayList<>();
        String query = "SELECT * FROM opciones";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Opciones opcion = new Opciones();
                opcion.setId_opcion(rs.getInt("id_opcion"));
                opcion.setOpcion(rs.getString("opcion"));
                opcion.setId_pregunta(rs.getInt("id_pregunta"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  opciones;
    }
}

