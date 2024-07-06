package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDao {

    public Administrador getOne(String matricula, String contraseña) {
        Administrador administrador = null;
        String query = "SELECT * FROM administrador WHERE matricula = ? AND contraseña = SHA2(?, 256)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, matricula);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador = new Administrador();
                    administrador.setMatricula(rs.getString("matricula"));
                    administrador.setNombre(rs.getString("nombre"));
                    administrador.setApellidoPaterno(rs.getString("apellido_paterno"));
                    administrador.setApellidoMaterno(rs.getString("apellido_materno"));
                    administrador.setCorreo(rs.getString("correo"));
                    administrador.setContraseña(rs.getString("contraseña"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrador;
    }

    //aca inserta
    public boolean insert(Administrador admin) {
        boolean flag = false;
        String query = "INSERT INTO Administrador (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, admin.getMatricula());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getApellidoPaterno());
            ps.setString(4, admin.getApellidoMaterno());
            ps.setString(5, admin.getCorreo());
            ps.setString(6, admin.getContraseña());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
