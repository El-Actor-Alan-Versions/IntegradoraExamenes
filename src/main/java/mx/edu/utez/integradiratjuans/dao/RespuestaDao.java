package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Respuesta;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RespuestaDao {
    public boolean insert(Respuesta respuesta){
        boolean flag = false;
        String query = "INSERT INTO Respuesta (Respueta,id_pregunta) VALUES (?,?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1,respuesta.getRespuesta());
            ps.setInt(2,respuesta.getId_pregunta());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

     public List<Respuesta> getAll(){
        List<Respuesta> respuestas = new ArrayList<>();
        String query = "SELECT * FROM Respuesta";

         try (Connection con = DatabaseConnectionManager.getConnection();
              PreparedStatement ps = con.prepareStatement(query)){
             ResultSet rs = ps.executeQuery();

             while (rs.next()) {
                 Respuesta respuesta = new Respuesta();
                 respuesta.setId_respuesta(rs.getInt("id_respuesta"));
                 respuesta.setRespuesta(rs.getString("respuesta"));
                 respuesta.setId_pregunta(rs.getInt("id_pregunta"));


             }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return respuestas;
     }

}
