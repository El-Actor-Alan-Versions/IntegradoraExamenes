package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Docente;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDao {

    public Docente getOne(String matricula, String contrasena) throws SQLException {
        String query = "SELECT * FROM Docente WHERE Matricula = ? AND Contraseña = SHA2(?, 256)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, matricula);
            statement.setString(2, contrasena); // La contraseña será cifrada usando SHA2 en la consulta

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Docente docente = new Docente();
                    docente.setMatricula(resultSet.getString("Matricula"));
                    docente.setNombre(resultSet.getString("Nombre"));
                    docente.setApellidoPaterno(resultSet.getString("Apellido_paterno"));
                    docente.setApellidoMaterno(resultSet.getString("Apellido_materno"));
                    docente.setCorreo(resultSet.getString("Correo"));
                    docente.setContraseña(resultSet.getString("Contraseña"));
                    // Asegúrate de tener un método setEstado en la clase Docente si no existe
                    docente.setEstado(resultSet.getString("estado"));
                    return docente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }


public Docente getById(String matricula) {
        Docente docente = null;
        String query = "SELECT * FROM Docente WHERE matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                docente = new Docente();
                docente.setMatricula(rs.getString("matricula"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellidoPaterno(rs.getString("apellido_paterno"));
                docente.setApellidoMaterno(rs.getString("apellido_materno"));
                docente.setCorreo(rs.getString("correo"));
                docente.setContraseña(rs.getString("contraseña"));
                docente.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docente;
    }

    public boolean insert(Docente docente) throws SQLException {
        String query = "INSERT INTO Docente (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, docente.getMatricula());
            statement.setString(2, docente.getNombre());
            statement.setString(3, docente.getApellidoPaterno());
            statement.setString(4, docente.getApellidoMaterno());
            statement.setString(5, docente.getCorreo());
            statement.setString(6, docente.getContraseña());
            statement.setString(7, docente.getEstado() != null ? docente.getEstado() : "activo"); // Valor predeterminado
            return statement.executeUpdate() > 0;
        }
    }

    public void updateEstado(String matricula, String nuevoEstado) throws SQLException {
        String query = "UPDATE Docente SET Estado = ? WHERE Matricula = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nuevoEstado);
            stmt.setString(2, matricula);
            stmt.executeUpdate();
        }
    }

    public List<Docente> getAll() throws SQLException {
        String query = "SELECT * FROM Docente";
        List<Docente> docentes = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Docente docente = new Docente();
                docente.setMatricula(resultSet.getString("Matricula"));
                docente.setNombre(resultSet.getString("Nombre"));
                docente.setApellidoPaterno(resultSet.getString("Apellido_paterno"));
                docente.setApellidoMaterno(resultSet.getString("Apellido_materno"));
                docente.setCorreo(resultSet.getString("Correo"));
                docente.setContraseña(resultSet.getString("Contraseña"));
                docente.setEstado(resultSet.getString("estado"));
                docentes.add(docente);
            }
        }
        return docentes;
    }


    public boolean update(Docente docente)  {
        boolean update = false;
        String query = "UPDATE Docente SET Nombre = ?, Apellido_paterno = ?, Apellido_materno = ?, Correo = ?, Contraseña = ? WHERE Matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, docente.getNombre());
            ps.setString(2, docente.getApellidoPaterno());
            ps.setString(3, docente.getApellidoMaterno());
            ps.setString(4, docente.getCorreo());
            ps.setString(5, docente.getContraseña());
            ps.setString(6, docente.getMatricula());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                update = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }

}
