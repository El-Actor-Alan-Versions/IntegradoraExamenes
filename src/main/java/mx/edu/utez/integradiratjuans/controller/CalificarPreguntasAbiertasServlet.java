package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.model.Preguntas;
import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Respuesta;
import mx.edu.utez.integradiratjuans.dao.PreguntaDao;
import mx.edu.utez.integradiratjuans.dao.OpcionesDao;
import mx.edu.utez.integradiratjuans.dao.RespuestaDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Docente/calificarExamen")
public class CalificarPreguntasAbiertasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int examenId = Integer.parseInt(request.getParameter("id_examen"));
        String matriculaEstudiante = request.getParameter("matricula_estudiante");
        System.out.println("Depuración - Examen ID: " + examenId);
        System.out.println("Depuración - Matrícula Estudiante: " + matriculaEstudiante);

        PreguntaDao preguntaDao = new PreguntaDao();
        OpcionesDao opcionesDao = new OpcionesDao();
        RespuestaDao respuestaDao = new RespuestaDao();

        // Obtener preguntas
        List<Preguntas> preguntas = preguntaDao.getPreguntasPorExamen(examenId);
        System.out.println("Depuración - Preguntas obtenidas: " + preguntas.size());

        Map<Integer, List<Opcion>> opcionesPorPregunta = new HashMap<>();

        // Obtener opciones para cada pregunta
        for (Preguntas pregunta : preguntas) {
            List<Opcion> opciones = null;
            try {
                opciones = opcionesDao.getOpcionesPorPregunta(pregunta.getIdPregunta());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.println("Depuración - Opciones para pregunta " + pregunta.getIdPregunta() + ": " + opciones.size());
            opcionesPorPregunta.put(pregunta.getIdPregunta(), opciones);
        }

        // Obtener respuestas del alumno
        Map<Integer, Respuesta> respuestasAlumno = respuestaDao.getRespuestasPorExamenYEstudiante(examenId, matriculaEstudiante);
        System.out.println("Depuración - Respuestas del alumno: " + respuestasAlumno.size());

        for (Map.Entry<Integer, Respuesta> entry : respuestasAlumno.entrySet()) {
            System.out.println("Depuración - Pregunta ID: " + entry.getKey() + ", Respuesta: " + entry.getValue().getRespuesta());
        }

        // Pasar datos a la JSP
        request.setAttribute("preguntas", preguntas);
        request.setAttribute("opcionesPorPregunta", opcionesPorPregunta);
        request.setAttribute("respuestasAlumno", respuestasAlumno);
        request.getRequestDispatcher("/Docente/calificarExamen.jsp").forward(request, response);
    }

}

