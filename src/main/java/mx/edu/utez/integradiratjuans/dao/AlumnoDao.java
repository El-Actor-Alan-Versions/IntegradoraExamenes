package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDao {

    public Alumno getOne(String matricula, String contraseña) {
        Alumno alumno = null;
        String query = "SELECT * FROM alumno WHERE matricula = ? AND contraseña = SHA2(?, 256)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, matricula);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno();
                    alumno.setMatricula(rs.getString("matricula"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellidoPaterno(rs.getString("apellido_paterno"));
                    alumno.setApellidoMaterno(rs.getString("apellido_materno"));
                    alumno.setCorreo(rs.getString("correo"));
                    alumno.setContraseña(rs.getString("contraseña"));
                    alumno.setIdGrupo(rs.getInt("id_grupo"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumno;
    }

    // Aquí podrías implementar métodos adicionales como insert, update, getAll, etc.
}
