package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Materia;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDao {

    // Obtener una materia por ID


        public Materia getById(int idMateria) throws SQLException {
             Materia materia = null;
            String query = "SELECT * FROM materia WHERE id_materia = ?";
            try (Connection conn = DatabaseConnectionManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, idMateria);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        materia = new Materia();
                        materia.setIdMateria(rs.getInt("id_materia"));
                        materia.setNombreMateria(rs.getString("nombre_materia"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return materia;
        }

    // Obtener todas las materias
    public List<Materia> getAll() {
        List<Materia> materias = new ArrayList<>();
        String query = "SELECT * FROM materia";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombreMateria(rs.getString("Nombre_materia"));
                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }

    // Insertar una nueva materia
    public boolean insert(Materia materia) {
        boolean inserted = false;
        String query = "INSERT INTO materia (Nombre_materia) VALUES (?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, materia.getNombreMateria());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                inserted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }

    // Actualizar una materia existente
    public boolean update(Materia materia) {
        boolean updated = false;
        String query = "UPDATE materia SET Nombre_materia = ? WHERE id_materia = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, materia.getNombreMateria());
            ps.setInt(2, materia.getIdMateria());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                updated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // Eliminar una materia por ID
    public boolean delete(int id) {
        boolean deleted = false;
        String query = "DELETE FROM materia WHERE id_materia = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                deleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }
}
