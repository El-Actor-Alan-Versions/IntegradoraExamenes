package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Docente;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocenteDao {

    // Función para obtener un docente por correo y contraseña
    public Docente getOne(String matricula, String contra) {
        Docente d = null;
        String query = "SELECT * FROM docente WHERE correo = ? AND contra = SHA2(?, 256)";

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
                // Entonces llenamos la información del docente
                d = new Docente();
                d.setMatricula(rs.getInt("id_docente"));
                d.setNombre(rs.getString("nombre"));
                d.setApellidoPaterno(rs.getString("apellido_paterno"));
                d.setApellidoMaterno(rs.getString("apellido_materno"));
                d.setCorreo(rs.getString("correo"));
                d.setContra(rs.getString("contra"));
                d.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    // Función para insertar un nuevo docente
    public boolean insert(Docente d) {
        boolean flag = false;
        String query = "INSERT INTO docente(nombre, apellido_paterno, apellido_materno, correo, contra, estado) VALUES (?, ?, ?, ?, SHA2(?, 256), ?)";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellidoPaterno());
            ps.setString(3, d.getApellidoMaterno());
            ps.setString(4, d.getCorreo());
            ps.setString(5, d.getContra());
            ps.setBoolean(6, d.getEstado());

            if (ps.executeUpdate() == 1) {
                flag = true; // Significa que se insertó en la BD
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    d.setIdDocente(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}

