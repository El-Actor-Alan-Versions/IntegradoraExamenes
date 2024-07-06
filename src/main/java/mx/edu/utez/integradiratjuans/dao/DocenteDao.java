package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Docente;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocenteDao {

    public Docente getOne(String matricula, String contraseña) {
        Docente docente = null;
        String query = "SELECT * FROM docente WHERE matricula = ? AND contraseña = SHA2(?, 256)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, matricula);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    docente = new Docente();
                    docente.setMatricula(rs.getString("matricula"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setApellidoPaterno(rs.getString("apellido_paterno"));
                    docente.setApellidoMaterno(rs.getString("apellido_materno"));
                    docente.setCorreo(rs.getString("correo"));
                    docente.setContraseña(rs.getString("contraseña"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docente;
    }

    public boolean insert(Docente docente) {
        boolean flag = false;
        String query = "INSERT INTO Docente (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, docente.getMatricula());
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellidoPaterno());
            ps.setString(4, docente.getApellidoMaterno());
            ps.setString(5, docente.getCorreo());
            ps.setString(6, docente.getContraseña());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }}
