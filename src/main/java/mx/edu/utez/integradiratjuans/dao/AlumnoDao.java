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

    public boolean insert(Alumno alumno) {
        boolean flag = false;
        String query = "INSERT INTO Alumno (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña, id_grupo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, alumno.getMatricula());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellidoPaterno());
            ps.setString(4, alumno.getApellidoMaterno());
            ps.setString(5, alumno.getCorreo());
            ps.setString(6, alumno.getContraseña());
            ps.setInt(7, alumno.getIdGrupo());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
