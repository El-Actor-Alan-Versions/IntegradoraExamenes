package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;

public class AlumnoDao {

    // Función para obtener un alumno con su matrícula y contraseña
    public Alumno getOne(String matricula, String contra) {
        Alumno a = new Alumno();
        String query = "SELECT * FROM alumno WHERE matricula = ? AND contra = SHA2(?, 256)";

        try {
            // 1) Conectarnos a la BD
            Connection con = DatabaseConnectionManager.getConnection();
            // 2) Configurar el query y ejecutarlo
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, matricula);
            ps.setString(2, contra);
            ResultSet rs = ps.executeQuery();
            // 3) Obtener la información
            if (rs.next()) {
                // Entonces llenamos la información del alumno
                a.setMatricula(rs.getString("matricula"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido_paterno(rs.getString("apellido_paterno"));
                a.setApellido_materno(rs.getString("apellido_materno"));
                a.setCorreo(rs.getString("correo"));
                a.setContra(rs.getString("contra"));
                a.setEstado(rs.getBoolean("estado"));
                a.setId_grupo(rs.getInt("id_grupo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    // Función para insertar un nuevo alumno
    public boolean insert(Alumno a) {
        boolean flag = false;
        String query = "INSERT INTO alumno(matricula, nombre, apellido_paterno, apellido_materno, correo, contra, estado, id_grupo) VALUES (?, ?, ?, ?, ?, SHA2(?, 256), ?, ?)";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, a.getMatricula());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido_paterno());
            ps.setString(4, a.getApellido_materno());
            ps.setString(5, a.getCorreo());
            ps.setString(6, a.getContra());
            ps.setBoolean(7, a.getEstado());
            ps.setInt(8, a.getId_grupo());
            if (ps.executeUpdate() == 1) {
                flag = true; // Significa que se insertó en la BD
                System.out.println("OK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
