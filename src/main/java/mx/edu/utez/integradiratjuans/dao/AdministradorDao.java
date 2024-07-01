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

    // Aquí podrías implementar métodos adicionales como insert, update, getAll, etc.
}
