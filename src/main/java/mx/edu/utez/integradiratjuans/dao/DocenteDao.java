package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Docente;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDao {

    public Docente getOne(String matricula, String contrasena) throws SQLException {
        String query = "SELECT * FROM Docente WHERE Matricula = ? AND Contraseña = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, matricula);
            statement.setString(2, contrasena); // Cuidado: las contraseñas en texto plano no son seguras
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Docente docente = new Docente();
                    docente.setMatricula(resultSet.getString("Matricula"));
                    docente.setNombre(resultSet.getString("Nombre"));
                    docente.setApellidoPaterno(resultSet.getString("Apellido_paterno"));
                    docente.setApellidoMaterno(resultSet.getString("Apellido_materno"));
                    docente.setCorreo(resultSet.getString("Correo"));
                    docente.setContraseña(resultSet.getString("Contraseña"));
                    docente.setEstado(resultSet.getString("estado"));
                    return docente;
                }
            }
        }
        return null;
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

    public boolean updateEstado(String matricula, String estado) {
        String query = "UPDATE Docente SET estado = ? WHERE Matricula = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, estado);
            statement.setString(2, matricula);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
}
