package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.model.Docente;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;
import mx.edu.utez.integradiratjuans.utils.HashingUtils;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    administrador.setCodigo_Recuperacion(rs.getString("codigo_recuperacion"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrador;
    }

    public Administrador getById(String matricula) {
        Administrador administrador = null;
        String query = "SELECT * FROM administrador WHERE matricula = ?";

        try(Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                administrador = new Administrador();
                administrador.setMatricula(rs.getString("matricula"));
                administrador.setNombre(rs.getString("nombre"));
                administrador.setApellidoPaterno(rs.getString("apellido_paterno"));
                administrador.setApellidoMaterno(rs.getString("apellido_materno"));
                administrador.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrador;
    }




    public List<Administrador> getAll() throws SQLException {
        String query = "SELECT * FROM Administrador WHERE ";
        List<Administrador> administrador = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Administrador admin = new Administrador();
                admin.setMatricula(resultSet.getString("Matricula"));
                admin.setNombre(resultSet.getString("Nombre"));
                admin.setApellidoPaterno(resultSet.getString("Apellido_paterno"));
                admin.setApellidoMaterno(resultSet.getString("Apellido_materno"));
                admin.setCorreo(resultSet.getString("Correo"));
                admin.setContraseña(resultSet.getString("Contraseña"));
                administrador.add(admin);
            }
        }
        return administrador;
    }

    //aca inserta
    public boolean insert(Administrador admin) {
        boolean flag = false;
        // Generar el correo basado en la matrícula
        String correo = admin.getMatricula() + "@utez.edu.mx";
        String query = "INSERT INTO Administrador (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, admin.getMatricula());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getApellidoPaterno());
            ps.setString(4, admin.getApellidoMaterno());
            ps.setString(5, correo); // Asignar el correo generado
            ps.setString(6, admin.getContraseña());
            if (ps.executeUpdate() == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String verTabla(String matricula) {
        String tabla = null;

        String sqlAdmin = "SELECT * FROM administrador WHERE matricula = ?";
        String sqlAlumno = "SELECT * FROM alumno WHERE matricula = ?";
        String sqlDocente = "SELECT * FROM docente WHERE matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection()) {

            // Verifica en la tabla Administrador
            try (PreparedStatement psAdmin = con.prepareStatement(sqlAdmin)) {
                psAdmin.setString(1, matricula);
                try (ResultSet rsAdmin = psAdmin.executeQuery()) {
                    if (rsAdmin.next()) {
                        tabla = "administrador";
                        return tabla;  // Retorna inmediatamente si se encuentra un resultado
                    }
                }
            }

            // Verifica en la tabla Alumno
            try (PreparedStatement psAlumno = con.prepareStatement(sqlAlumno)) {
                psAlumno.setString(1, matricula);
                try (ResultSet rsAlumno = psAlumno.executeQuery()) {
                    if (rsAlumno.next()) {
                        tabla = "alumno";
                        return tabla;  // Retorna inmediatamente si se encuentra un resultado
                    }
                }
            }

            // Verifica en la tabla Docente
            try (PreparedStatement psDocente = con.prepareStatement(sqlDocente)) {
                psDocente.setString(1, matricula);
                try (ResultSet rsDocente = psDocente.executeQuery()) {
                    if (rsDocente.next()) {
                        tabla = "docente";
                        return tabla;  // Retorna inmediatamente si se encuentra un resultado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tabla;  // Retorna null si no se encontró en ninguna tabla
    }

    public Administrador getByEmail(String email) {
        Administrador a = null;
        String query = "SELECT * FROM Administrador WHERE correo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Administrador();
                a.setMatricula(rs.getString("Matricula"));
                a.setCorreo(rs.getString("correo"));
                a.setCodigo_Recuperacion(rs.getString("codigo_recuperacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public boolean updateCodigoRecuperacion(Administrador a) {
        boolean flag = false;
        String query = "UPDATE Administrador SET codigo_recuperacion = ? WHERE matricula = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, a.getCodigo_Recuperacion());
            ps.setString(2, a.getMatricula());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateContraseña(Administrador administrador) {
        boolean flag = false;
        String query = "UPDATE Administrador SET Contraseña = ?, codigo_recuperacion = NULL WHERE Matricula = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, HashingUtils.hashPassword(administrador.getContraseña())); // Asegúrate de encriptar la contraseña
            ps.setString(2, administrador.getMatricula()); // Corregido: Cambiado de codigo_recuperacion a matricula
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }



    public boolean update(Administrador administrador) {
        boolean update = false;
        String query = "UPDATE Administrador SET Nombre = ?, Apellido_paterno = ?, Apellido_materno = ?, Correo = ?, Contraseña = ? WHERE Matricula = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, administrador.getNombre());
            ps.setString(2, administrador.getApellidoPaterno());
            ps.setString(3, administrador.getApellidoMaterno());
            ps.setString(4, administrador.getCorreo());
            ps.setString(5, HashingUtils.hashPassword(administrador.getContraseña())); // Asegúrate de encriptar la contraseña
            ps.setString(6, administrador.getMatricula());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                update = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }



    public Administrador getByCodigoRecuperacion(String codigo) {
        Administrador a = null;
        String query = "SELECT * FROM Administrador WHERE codigo_recuperacion = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new Administrador();
                    a.setMatricula(rs.getString("matricula"));
                    a.setCorreo(rs.getString("correo"));
                    a.setCodigo_Recuperacion(rs.getString("codigo_recuperacion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }


}