package mx.edu.utez.integradiratjuans.dao;

import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Preguntas;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpcionesDao {

    // Método para insertar preguntas y opciones
    public void insertarPreguntas(List<Preguntas> preguntas) throws SQLException {
        String sqlPregunta = "INSERT INTO pregunta (pregunta, id_examen) VALUES (?, ?)";
        String sqlOpcion = "INSERT INTO opciones (opcion, id_pregunta, correcta) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmtPregunta = connection.prepareStatement(sqlPregunta, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtOpcion = connection.prepareStatement(sqlOpcion)) {

            for (Preguntas pregunta : preguntas) {
                // Insertar pregunta
                stmtPregunta.setString(1, pregunta.getTexto());
                stmtPregunta.setInt(2, pregunta.getIdExamen());

                int affectedRows = stmtPregunta.executeUpdate();
                if (affectedRows > 0) {
                    try (var generatedKeys = stmtPregunta.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int idPregunta = generatedKeys.getInt(1);
                            pregunta.setIdPregunta(idPregunta);

                            // Insertar opciones para la pregunta
                            for (var opcion : pregunta.getOpciones()) {
                                stmtOpcion.setString(1, opcion.getOpcion());
                                stmtOpcion.setInt(2, idPregunta);
                                stmtOpcion.setBoolean(3, opcion.isCorrecta());
                                stmtOpcion.addBatch();
                            }
                        }
                    }
                }
            }

            // Ejecutar las inserciones de opciones en batch
            stmtOpcion.executeBatch();
        }
    }

    // Método para obtener opciones por ID de pregunta
    public List<Opcion> obtenerOpcionesPorPregunta(int idPregunta) {
        List<Opcion> opciones = new ArrayList<>();
        String query = "SELECT id_opcion, opcion, id_pregunta, correcta FROM opciones WHERE id_pregunta = ?";

        System.out.println("Ejecutando consulta: " + query + " con ID de pregunta: " + idPregunta);

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idPregunta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Opcion opcion = new Opcion();
                opcion.setIdOpcion(rs.getInt("id_opcion"));
                opcion.setOpcion(rs.getString("opcion"));
                opcion.setIdPregunta(rs.getInt("id_pregunta"));
                opcion.setCorrecta(rs.getBoolean("correcta"));
                opciones.add(opcion);
            }
            System.out.println("Opciones obtenidas para la pregunta ID " + idPregunta + ": " + opciones.size());
        } catch (SQLException e) {
            System.err.println("Error obteniendo opciones: " + e.getMessage());
        }
        return opciones;
    }

    public List<Opcion> getOpcionesPorPregunta(int idPregunta) throws SQLException {
        List<Opcion> opciones = new ArrayList<>();
        String sql = "SELECT id_opcion, opcion, correcta FROM opciones WHERE id_pregunta = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Opcion opcion = new Opcion();
                opcion.setIdOpcion(rs.getInt("id_opcion"));
                opcion.setOpcion(rs.getString("opcion"));
                opcion.setCorrecta(rs.getBoolean("correcta"));
                opciones.add(opcion);
            }
        }
        return opciones;
    }

    // Método para obtener todas las opciones
    public List<Opcion> getAll() {
        List<Opcion> opciones = new ArrayList<>();
        String query = "SELECT * FROM opciones";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Opcion opcion = new Opcion();
                opcion.setIdOpcion(rs.getInt("id_opcion"));
                opcion.setOpcion(rs.getString("opcion"));
                opcion.setIdPregunta(rs.getInt("id_pregunta"));
                opcion.setCorrecta(rs.getBoolean("correcta"));
                opciones.add(opcion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return opciones;
    }

    // Método para obtener opciones por ID de pregunta
    public List<Opcion> getOpcionesByIdPregunta(int idPregunta) {
        List<Opcion> opciones = new ArrayList<>();
        String sql = "SELECT * FROM opciones WHERE id_pregunta = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPregunta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Opcion opcion = new Opcion();
                    opcion.setIdOpcion(rs.getInt("id_opcion"));
                    opcion.setOpcion(rs.getString("opcion"));
                    opcion.setIdPregunta(rs.getInt("id_pregunta"));
                    opcion.setCorrecta(rs.getBoolean("correcta")); // Suponiendo que "correcta" es un BOOLEAN en la base de datos
                    opciones.add(opcion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return opciones;
    }
    public Opcion getRespuestaCorrecta(int idPregunta) throws SQLException {
        String sql = "SELECT id_opcion, opcion, correcta FROM opciones WHERE id_pregunta = ? AND correcta = true";
        Opcion opcionCorrecta = null;

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPregunta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                opcionCorrecta = new Opcion();
                opcionCorrecta.setIdOpcion(rs.getInt("id_opcion"));
                opcionCorrecta.setOpcion(rs.getString("opcion"));
                opcionCorrecta.setCorrecta(rs.getBoolean("correcta"));
            }
        }

        return opcionCorrecta;
    }
}
