package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao {

    public Alumno getOne(String matricula, String contrasena) throws SQLException {
        String query = "SELECT * FROM Alumno WHERE Matricula = ? AND Contraseña = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, matricula);
            statement.setString(2, contrasena); // Cuidado: las contraseñas en texto plano no son seguras
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setMatricula(resultSet.getString("Matricula"));
                    alumno.setNombre(resultSet.getString("Nombre"));
                    alumno.setApellidoPaterno(resultSet.getString("Apellido_paterno"));
                    alumno.setApellidoMaterno(resultSet.getString("Apellido_materno"));
                    alumno.setCorreo(resultSet.getString("Correo"));
                    alumno.setContraseña(resultSet.getString("Contraseña"));
                    alumno.setIdGrupo(resultSet.getInt("id_grupo"));
                    alumno.setEstado(resultSet.getString("estado"));
                    return alumno;
                }
            }
        }
        return null;
    }


    public boolean insert(Alumno alumno) throws SQLException {
        String query = "INSERT INTO Alumno (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña, id_grupo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, alumno.getMatricula());
            statement.setString(2, alumno.getNombre());
            statement.setString(3, alumno.getApellidoPaterno());
            statement.setString(4, alumno.getApellidoMaterno());
            statement.setString(5, alumno.getCorreo());
            statement.setString(6, alumno.getContraseña());
            statement.setInt(7, alumno.getIdGrupo());
            statement.setString(8, alumno.getEstado() != null ? alumno.getEstado() : "activo"); // Valor predeterminado
            return statement.executeUpdate() > 0;
        }
    }

    public void updateEstado(String matricula, String nuevoEstado) throws SQLException {
        String query = "UPDATE Alumno SET Estado = ? WHERE Matricula = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nuevoEstado);
            stmt.setString(2, matricula);
            stmt.executeUpdate();
        }
    }


    public List<Alumno> getAll() throws SQLException {
        String query = "SELECT * FROM Alumno";
        List<Alumno> alumnos = new ArrayList<>();
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Alumno alumno = new Alumno();
                alumno.setMatricula(resultSet.getString("Matricula"));
                alumno.setNombre(resultSet.getString("Nombre"));
                alumno.setApellidoPaterno(resultSet.getString("Apellido_paterno"));
                alumno.setApellidoMaterno(resultSet.getString("Apellido_materno"));
                alumno.setCorreo(resultSet.getString("Correo"));
                alumno.setContraseña(resultSet.getString("Contraseña"));
                alumno.setIdGrupo(resultSet.getInt("id_grupo"));
                alumno.setEstado(resultSet.getString("estado"));
                alumnos.add(alumno);
            }
        }
        return alumnos;
    }
}
