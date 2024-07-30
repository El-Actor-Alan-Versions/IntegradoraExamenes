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



    public void insertarPreguntas(List<Preguntas> preguntas) throws SQLException {
        String sqlPregunta = "INSERT INTO Pregunta (pregunta, id_examen) VALUES (?, ?)";
        String sqlOpcion = "INSERT INTO Opciones (opcion, id_pregunta, correcta) VALUES (?, ?, ?)";

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

    private void insertarOpciones(Preguntas pregunta) throws SQLException {
        String sql = "INSERT INTO Opciones (opcion, id_pregunta, correcta) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (var opcion : pregunta.getOpciones()) {
                statement.setString(1, opcion.getOpcion());
                statement.setInt(2, pregunta.getIdPregunta());
                statement.setBoolean(3, opcion.isCorrecta());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public List<Opcion> getAll() {
        List<Opcion> opciones = new ArrayList<>();
        String query = "SELECT * FROM Opciones";

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
}
