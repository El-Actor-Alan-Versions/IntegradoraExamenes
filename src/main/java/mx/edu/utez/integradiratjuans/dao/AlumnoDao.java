package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.model.Docente;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;
import mx.edu.utez.integradiratjuans.utils.HashingUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao {

    public Alumno getOne(String matricula, String contrasena) throws SQLException {
        String query = "SELECT * FROM alumno WHERE Matricula = ? AND Contraseña = SHA2(?, 256)";
        ;
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

        String query = "INSERT INTO alumno (Matricula, Nombre, Apellido_paterno, Apellido_materno, Correo, Contraseña, id_grupo, estado) VALUES (?, ?, ?, ?, ?, SHA2(?, 256), ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, alumno.getMatricula());
            statement.setString(2, alumno.getNombre());
            statement.setString(3, alumno.getApellidoPaterno());
            statement.setString(4, alumno.getApellidoMaterno());
            statement.setString(5, alumno.getCorreo()); // Generar correo automáticamente
            statement.setString(6, alumno.getContraseña());
            statement.setInt(7, alumno.getIdGrupo());
            statement.setString(8, alumno.getEstado() != null ? alumno.getEstado() : "activo");
            return statement.executeUpdate() > 0;
        }
    }


    public void updateEstado(String matricula, String nuevoEstado) throws SQLException {
        String query = "UPDATE alumno SET Estado = ? WHERE Matricula = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nuevoEstado);
            stmt.setString(2, matricula);
            stmt.executeUpdate();
        }
    }




    public List<Alumno> getAll() {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT a.*, g.Grado_grupo " +
                "FROM alumno a " +
                "JOIN grupo g ON a.id_grupo = g.id_grupo";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setMatricula(rs.getString("Matricula"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setApellidoPaterno(rs.getString("Apellido_paterno"));
                alumno.setApellidoMaterno(rs.getString("Apellido_materno"));
                alumno.setCorreo(rs.getString("Correo"));
                alumno.setEstado(rs.getString("Estado"));
                alumno.setNombreGrupo(rs.getString("Grado_grupo")); // Asegúrate de tener este setter en tu modelo Alumno
                alumnos.add(alumno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumnos;
    }

    public Alumno getById(String matricula) {
        Alumno alumno = null;
        String query = "SELECT * FROM alumno WHERE matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellidoPaterno(rs.getString("apellido_paterno"));
                alumno.setApellidoMaterno(rs.getString("apellido_materno"));
                alumno.setCorreo(rs.getString("correo"));
                alumno.setContraseña(rs.getString("contraseña"));
                alumno.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumno;
    }

    public boolean update(Alumno alumno) {
        boolean update = false;
        String query = "UPDATE alumno SET Nombre = ?, Apellido_paterno = ?, Apellido_materno = ?, Correo = ?, Contraseña = ?, Id_grupo = ? WHERE Matricula = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellidoPaterno());
            ps.setString(3, alumno.getApellidoMaterno());
            ps.setString(4, alumno.getCorreo());
            ps.setString(5, alumno.getContraseña()); // Contraseña ya debe estar hasheada
            ps.setInt(6, alumno.getIdGrupo());
            ps.setString(7, alumno.getCodigo_Recuperacion()); // Añadido para actualizar el código de recuperación
            ps.setString(8, alumno.getMatricula());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                update = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return update;
    }


    public boolean updateContra(String matricula, String nuevaContraseña) {
        boolean updated = false;
        String query = "UPDATE alumno SET Contraseña = SHA2(?, 256) WHERE Matricula = ?";

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


    public boolean updateContraseña(String matricula, String nuevaContraseña) {
        boolean updated = false;
        String query = "UPDATE alumno SET Contraseña = SHA2(?, 256), Codigo_recuperacion = NULL WHERE Matricula = ?";

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



    public List<String> obtenerMatriculasPorIdGrupo(int idGrupo) {
        List<String> matriculas = new ArrayList<>();
        String sql = "SELECT matricula FROM alumno WHERE id_grupo = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idGrupo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                matriculas.add(resultSet.getString("matricula"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matriculas;
    }


    public Alumno getByEmail(String email) {
        Alumno a = null;
        String query = "SELECT * FROM alumno WHERE correo = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Alumno();
                a.setMatricula(rs.getString("Matricula"));
                a.setCorreo(rs.getString("correo"));
                a.setCodigo_Recuperacion(rs.getString("codigo_recuperacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public boolean updateContraseña(Alumno alumno) {
        boolean flag = false;
        String query = "UPDATE alumno SET Contraseña = ?, codigo_recuperacion = NULL WHERE Matricula = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, HashingUtils.hashPassword(alumno.getContraseña())); // Asegúrate de encriptar la contraseña
            ps.setString(2, alumno.getMatricula());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }




    public boolean updateCodigoRecuperacion(Alumno a) {
        boolean flag = false;
        String query = "UPDATE alumno SET codigo_recuperacion = ? WHERE matricula = ?";
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


    public Alumno getByCodigoRecuperacion(String codigo) {
        Alumno a = null;
        String query = "SELECT * FROM alumno WHERE codigo_recuperacion = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new Alumno();
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