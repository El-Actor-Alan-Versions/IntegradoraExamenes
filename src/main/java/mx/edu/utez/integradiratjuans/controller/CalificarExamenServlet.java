package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.PreguntaDao;
import mx.edu.utez.integradiratjuans.dao.OpcionesDao;
import mx.edu.utez.integradiratjuans.model.Preguntas;
import mx.edu.utez.integradiratjuans.utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/Alumno/CalificarExamen")
public class CalificarExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            double calificacion = 0;

            // Obtén las preguntas del examen desde el request
            @SuppressWarnings("unchecked")
            List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");

            if (preguntas == null || preguntas.isEmpty()) {
                throw new ServletException("No se encontraron preguntas para calificar.");
            }

            // Obtén el ID del examen y la matrícula del alumno desde la sesión
            int idExamen = (int) request.getSession().getAttribute("idExamen");
            String matriculaAlumno = (String) request.getSession().getAttribute("matriculaAlumno");

            // Conectar a la base de datos
            try (Connection connection = DatabaseConnectionManager.getConnection()) {
                PreguntaDao preguntaDao = new PreguntaDao();
                OpcionesDao opcionDao = new OpcionesDao();

                // Califica el examen basado en las respuestas proporcionadas
                calificacion = calificarExamen(request, preguntas, preguntaDao, opcionDao);

                // Guarda la calificación en la base de datos
                guardarCalificacion(matriculaAlumno, idExamen, calificacion, connection);

                response.sendRedirect("exito.jsp"); // Redirige a la página de éxito

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error al acceder a la base de datos.", e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirige a la página de error
        }
    }

    private double calificarExamen(HttpServletRequest request, List<Preguntas> preguntas, PreguntaDao preguntaDao, OpcionesDao opcionDao) throws SQLException {
        double calificacion = 0;

        for (Preguntas pregunta : preguntas) {
            String tipoPregunta = pregunta.getTipo();
            String idPregunta = "pregunta_" + pregunta.getIdPregunta();
            String respuestaAlumno = request.getParameter(idPregunta);

            if (respuestaAlumno == null) {
                continue;
            }

            boolean respuestaCorrecta = false;

            switch (tipoPregunta) {
                case "opcion_multiple":
                case "verdaderoFalso":
                    String respuestaCorrectaOpcion = preguntaDao.getRespuestaCorrecta(pregunta.getIdPregunta());
                    if (respuestaAlumno.equals(respuestaCorrectaOpcion)) {
                        respuestaCorrecta = true;
                    }
                    break;

                case "varias_respuestas":
                    List<String> respuestasCorrectas = preguntaDao.getRespuestasCorrectas(pregunta.getIdPregunta());
                    String[] respuestasAlumno = request.getParameterValues(idPregunta);

                    if (respuestasAlumno != null) {
                        List<String> respuestasAlumnoList = Arrays.asList(respuestasAlumno);
                        if (respuestasAlumnoList.containsAll(respuestasCorrectas) && respuestasCorrectas.containsAll(respuestasAlumnoList)) {
                            respuestaCorrecta = true;
                        }
                    }
                    break;

                case "abierta":
                    // Lógica para preguntas abiertas (aún no implementada)
                    break;
            }

            if (respuestaCorrecta) {
                calificacion += preguntaDao.getPuntajePregunta(pregunta.getIdPregunta());
            }
        }

        return calificacion;
    }

    private void guardarCalificacion(String matriculaAlumno, int idExamen, double calificacion, Connection connection) throws SQLException {
        String sql = "INSERT INTO Calificaciones (matricula_alumno, id_examen, calificacion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matriculaAlumno);
            stmt.setInt(2, idExamen);
            stmt.setDouble(3, calificacion);
            stmt.executeUpdate();
        }
    }
}
