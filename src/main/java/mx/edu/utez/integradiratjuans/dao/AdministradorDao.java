package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDao {

    public Administrador getOne(String matricula, String contraseña) {
        Administrador administrador = null;
        String query = "SELECT * FROM Administrador WHERE matricula = ? AND contraseña = SHA2(?, 256)";

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
        String query = "SELECT * FROM Administrador WHERE Matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, matricula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador = extractAdministradorFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrador;
    }

    public List<Administrador> getAll() {
        List<Administrador> administradores = new ArrayList<>();
        String query = "SELECT * FROM Administrador";

        try (Connection con = DatabaseConnectionManager.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Administrador administrador = extractAdministradorFromResultSet(rs);
                administradores.add(administrador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administradores;
    }

    public boolean insert(Administrador admin) {
        boolean inserted = false;

        String query = "INSERT INTO Administrador (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña) VALUES (?, ?, ?, ?, ?, SHA2(?, 256))";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, admin.getMatricula());
            ps.setString(2, admin.getNombre());
            ps.setString(3, admin.getApellidoPaterno());
            ps.setString(4, admin.getApellidoMaterno());
            ps.setString(5, admin.getCorreo());
            ps.setString(6, admin.getContraseña());

            inserted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }

    public String verTabla(String matricula) {
        String tabla = null;

        String[] queries = {
                "SELECT 'Administrador' AS tabla FROM Administrador WHERE Matricula = ?",
                "SELECT 'Alumno' AS tabla FROM Alumno WHERE Matricula = ?",
                "SELECT 'Docente' AS tabla FROM Docente WHERE Matricula = ?"
        };

        try (Connection con = DatabaseConnectionManager.getConnection()) {

            for (String query : queries) {
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, matricula);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            tabla = rs.getString("tabla");
                            return tabla; // Retorna inmediatamente si se encuentra un resultado
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tabla;  // Retorna null si no se encontró en ninguna tabla
    }

    public Administrador getByEmail(String email) {
        Administrador administrador = null;
        String query = "SELECT * FROM Administrador WHERE Correo = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador = extractAdministradorFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrador;
    }

    public boolean updateCodigoRecuperacion(Administrador administrador) {
        boolean updated = false;
        String query = "UPDATE Administrador SET Codigo_recuperacion = ? WHERE Matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, administrador.getCodigo_Recuperacion());
            ps.setString(2, administrador.getMatricula());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public boolean updateContraseña(String matricula, String nuevaContraseña) {
        boolean updated = false;
        String query = "UPDATE Administrador SET Contraseña = SHA2(?, 256), Codigo_recuperacion = NULL WHERE Matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, nuevaContraseña);
            ps.setString(2, matricula);

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }


    public boolean update(Administrador administrador) {
        boolean updated = false;
        String query = "UPDATE Administrador SET Nombre = ?, Apellido_paterno = ?, Apellido_materno = ?, Correo = ?, Contraseña = SHA2(?, 256) WHERE Matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, administrador.getNombre());
            ps.setString(2, administrador.getApellidoPaterno());
            ps.setString(3, administrador.getApellidoMaterno());
            ps.setString(4, administrador.getCorreo());
            ps.setString(5, administrador.getContraseña());  // Contraseña se pasa en texto plano, el hashing se realiza en la consulta SQL
            ps.setString(6, administrador.getMatricula());

            updated = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public Administrador getByCodigoRecuperacion(String codigo) {
        Administrador administrador = null;
        String query = "SELECT * FROM Administrador WHERE Codigo_recuperacion = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador = extractAdministradorFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrador;
    }

    // Método auxiliar para extraer un Administrador de un ResultSet
    private Administrador extractAdministradorFromResultSet(ResultSet rs) throws SQLException {
        Administrador administrador = new Administrador();
        administrador.setMatricula(rs.getString("Matricula"));
        administrador.setNombre(rs.getString("Nombre"));
        administrador.setApellidoPaterno(rs.getString("Apellido_paterno"));
        administrador.setApellidoMaterno(rs.getString("Apellido_materno"));
        administrador.setCorreo(rs.getString("Correo"));
        administrador.setContraseña(rs.getString("Contraseña"));
        administrador.setCodigo_Recuperacion(rs.getString("Codigo_recuperacion"));
        return administrador;
    }
}


