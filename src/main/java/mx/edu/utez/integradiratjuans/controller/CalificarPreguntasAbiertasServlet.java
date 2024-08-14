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

        PreguntaDao preguntaDao = new PreguntaDao();
        OpcionesDao opcionesDao = new OpcionesDao();
        RespuestaDao respuestaDao = new RespuestaDao();

        // Obtener preguntas
        List<Preguntas> preguntas = preguntaDao.getPreguntasPorExamen(examenId);

        Map<Integer, List<Opcion>> opcionesPorPregunta = new HashMap<>();
        Map<Integer, Integer> calificaciones = new HashMap<>();

        // Obtener opciones para cada pregunta
        for (Preguntas pregunta : preguntas) {
            List<Opcion> opciones;
            try {
                opciones = opcionesDao.getOpcionesPorPregunta(pregunta.getIdPregunta());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            opcionesPorPregunta.put(pregunta.getIdPregunta(), opciones);

            // Obtener la respuesta correcta
            Opcion opcionCorrecta = opciones.stream().filter(Opcion::isCorrecta).findFirst().orElse(null);

            // Obtener respuesta del alumno
            Respuesta respuestaAlumno = respuestaDao.getRespuestasPorExamenYEstudiante(examenId, matriculaEstudiante).get(pregunta.getIdPregunta());

            // Comparar la respuesta del alumno con la correcta
            if (respuestaAlumno != null && opcionCorrecta != null) {
                calificaciones.put(pregunta.getIdPregunta(), opcionCorrecta.getOpcion().equals(respuestaAlumno.getRespuesta()) ? 1 : 0);
            } else {
                calificaciones.put(pregunta.getIdPregunta(), 0);
            }
        }

        // Pasar datos a la JSP
        request.setAttribute("preguntas", preguntas);
        request.setAttribute("opcionesPorPregunta", opcionesPorPregunta);
        request.setAttribute("respuestasAlumno", respuestaDao.getRespuestasPorExamenYEstudiante(examenId, matriculaEstudiante));
        request.setAttribute("calificaciones", calificaciones);
        request.getRequestDispatcher("/Docente/calificarExamen.jsp").forward(request, response);
    }
}
