package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.CalificacionDao;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.dao.RespuestaDao;
import mx.edu.utez.integradiratjuans.model.Calificacion;
import mx.edu.utez.integradiratjuans.model.Respuesta;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/Alumno/EnviarRespuestas")
public class RegistrarPreguntasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String matriculaAlumno = (String) session.getAttribute("matriculaAlumno");
        Integer idExamen = Integer.parseInt(request.getParameter("id_examen"));
        List<Integer> idPreguntas = (List<Integer>) session.getAttribute("idPreguntas");

        if (idPreguntas == null || matriculaAlumno == null || idExamen == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se encontraron datos necesarios en la sesión.");
            return;
        }

        RespuestaDao respuestaDao = new RespuestaDao();
        CalificacionDao calificacionDao = new CalificacionDao();
        Map<Integer, List<String>> respuestas = new HashMap<>();
        int totalRespuestas = 0;
        int respuestasCorrectas = 0;

        // Recoger todas las respuestas enviadas por el usuario
        for (Integer idPregunta : idPreguntas) {
            List<String> respuestasPregunta = new ArrayList<>();
            String[] respuestaIds = request.getParameterValues("pregunta_" + idPregunta);
            if (respuestaIds != null) {
                respuestasPregunta.addAll(Arrays.asList(respuestaIds));
            }
            respuestas.put(idPregunta, respuestasPregunta);
        }

        // Procesar cada pregunta
        for (Integer idPregunta : idPreguntas) {
            List<String> respuestasUsuario = respuestas.get(idPregunta);
            if (respuestasUsuario == null || respuestasUsuario.isEmpty()) {
                continue; // Pasar a la siguiente pregunta si no hay respuestas
            }

            // Obtener las opciones correctas desde la base de datos
            List<String> opcionesCorrectas = respuestaDao.CompararRespuestas(idPregunta);

            if (opcionesCorrectas.isEmpty()) {
                continue; // Pasar a la siguiente pregunta si no hay opción correcta
            }

            // Verificar si alguna de las respuestas enviadas es correcta
            boolean esCorrecto = respuestasUsuario.stream().anyMatch(opcionesCorrectas::contains);

            // Insertar la respuesta en la base de datos
            Respuesta respuesta = new Respuesta();
            respuesta.setIdPregunta(idPregunta);
            respuesta.setAcierto(esCorrecto ? 1 : 0);
            respuesta.setMatriculaEstudiante(matriculaAlumno); // Agregar matrícula del estudiante
            boolean insertado = respuestaDao.insert(respuesta);

            if (insertado) {
                totalRespuestas++;
                if (esCorrecto) {
                    respuestasCorrectas++;
                }
            }
        }

        // Calcular calificación
        double calificacion = (totalRespuestas > 0) ? (double) respuestasCorrectas / totalRespuestas * 100 : 0.0;

        // Insertar calificación en la base de datos
        Calificacion calificacionObj = new Calificacion();
        calificacionObj.setMatriculaAlumno(matriculaAlumno);
        calificacionObj.setIdExamen(idExamen);
        calificacionObj.setCalificacion(calificacion);
        calificacionObj.setFecha(new Timestamp(System.currentTimeMillis()));

        boolean calificacionInsertada = calificacionDao.insert(calificacionObj);

        if (!calificacionInsertada) {
            System.out.println("Fallo al insertar la calificación para el examen: " + idExamen);
        }

        // Actualizar el estado del examen
        ExamenDao examenDao = new ExamenDao();
        boolean estadoActualizado = examenDao.updateEstadoExamen(idExamen);

        if (estadoActualizado) {
            System.out.println("Estado del examen actualizado correctamente.");
        } else {
            System.out.println("Fallo al actualizar el estado del examen.");
        }

        // Redirigir a una página de éxito o mostrar un mensaje
        response.sendRedirect("verCalificaciones.jsp");
    }
}
