package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.PreguntaDao;
import mx.edu.utez.integradiratjuans.dao.OpcionesDao;
import mx.edu.utez.integradiratjuans.model.Preguntas;
import mx.edu.utez.integradiratjuans.model.Opcion;
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
        double calificacion = 0;
        try {
            List<Preguntas> preguntas = (List<Preguntas>) request.getAttribute("preguntas");

            if (preguntas == null || preguntas.isEmpty()) {
                response.sendRedirect("error.jsp?msg=No se encontraron preguntas para calificar.");
                return;
            }

            Integer idExamen = (Integer) request.getSession().getAttribute("idExamen");
            String matriculaAlumno = (String) request.getSession().getAttribute("matriculaAlumno");

            if (idExamen == null || matriculaAlumno == null) {
                response.sendRedirect("error.jsp?msg=ID de examen o matrícula de alumno no válidos.");
                return;
            }
            System.out.println("coneccion aun no hecha");

            try (Connection connection = DatabaseConnectionManager.getConnection()) {
                PreguntaDao preguntaDao = new PreguntaDao();
                OpcionesDao opcionDao = new OpcionesDao();

                System.out.println("coneccion exitosa");

                calificacion = calificarExamen(request, preguntas, preguntaDao, opcionDao);

                guardarCalificacion(matriculaAlumno, idExamen, calificacion, connection);

                response.sendRedirect("exito.jsp");

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Error al acceder a la base de datos.", e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?msg=Ocurrió un error inesperado.");
        }
    }

    private double calificarExamen(HttpServletRequest request, List<Preguntas> preguntas, PreguntaDao preguntaDao, OpcionesDao opcionDao) throws SQLException {
        double calificacion = 0;
        System.out.println("Número de preguntas: " + preguntas.size());

        for (Preguntas pregunta : preguntas) {
            String tipoPregunta = pregunta.getTipo();
            String idPregunta = "pregunta_" + pregunta.getIdPregunta();
            String[] respuestasAlumno = request.getParameterValues(idPregunta);
            System.out.println("Tipo de pregunta: " + tipoPregunta);
            System.out.println("ID Pregunta: " + idPregunta);
            System.out.println("Respuestas del alumno: " + Arrays.toString(respuestasAlumno));

            if (respuestasAlumno == null || respuestasAlumno.length == 0) {
                continue;
            }

            boolean respuestaCorrecta = false;

            switch (tipoPregunta) {
                case "opcion_multiple":
                    String respuestaCorrectaOM = preguntaDao.getRespuestaCorrecta(pregunta.getIdPregunta());
                    if (respuestaCorrectaOM != null && Arrays.asList(respuestasAlumno).contains(respuestaCorrectaOM)) {
                        respuestaCorrecta = true;
                    }
                    break;

                case "verdadero_falso":
                    String respuestaCorrectaVF = preguntaDao.getRespuestaCorrecta(pregunta.getIdPregunta()).toLowerCase();
                    if (respuestaCorrectaVF != null && Arrays.asList(respuestasAlumno).contains(respuestaCorrectaVF)) {
                        respuestaCorrecta = true;
                    }
                    break;

                case "varias_respuestas":
                    List<String> respuestasCorrectas = preguntaDao.getRespuestasCorrectas(pregunta.getIdPregunta());
                    List<String> respuestasAlumnoList = Arrays.asList(respuestasAlumno);
                    if (respuestasAlumnoList.containsAll(respuestasCorrectas) && respuestasCorrectas.containsAll(respuestasAlumnoList)) {
                        respuestaCorrecta = true;
                    }
                    break;

                case "abierta":
                    // Implementar lógica para preguntas abiertas si es necesario
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
