package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.RespuestaDao;
import mx.edu.utez.integradiratjuans.model.Respuesta;

import java.io.IOException;
import java.util.*;

@WebServlet("/Alumno/EnviarRespuestas")
public class RegistrarPreguntasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Integer> idPreguntas = (List<Integer>) session.getAttribute("idPreguntas");

        if (idPreguntas == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se encontraron IDs de preguntas en la sesión.");
            return;
        }

        RespuestaDao respuestaDao = new RespuestaDao();
        Map<Integer, List<String>> respuestas = new HashMap<>();

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
                System.out.println("No se recibieron respuestas para la pregunta: " + idPregunta);
                continue; // Pasar a la siguiente pregunta si no hay respuestas
            }

            // Obtener las opciones correctas desde la base de datos
            List<String> opcionesCorrectas = respuestaDao.CompararRespuestas(idPregunta);

            if (opcionesCorrectas.isEmpty()) {
                System.out.println("No se encontraron opciones correctas para la pregunta: " + idPregunta);
                continue; // Pasar a la siguiente pregunta si no hay opción correcta
            }

            // Verificar si alguna de las respuestas enviadas es correcta
            boolean esCorrecto = respuestasUsuario.stream().anyMatch(opcionesCorrectas::contains);

            // Insertar la respuesta en la base de datos
            Respuesta respuesta = new Respuesta();
            respuesta.setIdPregunta(idPregunta);
            respuesta.setAcierto(esCorrecto ? 1 : 0);
            boolean insertado = respuestaDao.insert(respuesta);

            if (!insertado) {
                System.out.println("Fallo al insertar la respuesta para la pregunta: " + idPregunta);
            }
        }

        // Redirigir a una página de éxito o mostrar un mensaje
        response.sendRedirect("resultado.jsp");
    }
}
