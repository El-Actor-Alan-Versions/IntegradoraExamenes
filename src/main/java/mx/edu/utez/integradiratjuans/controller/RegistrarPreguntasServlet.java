package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.RespuestaDao;
import mx.edu.utez.integradiratjuans.dao.OpcionesDao;
import mx.edu.utez.integradiratjuans.model.Respuesta;
import mx.edu.utez.integradiratjuans.model.Preguntas;

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
        for (Integer idPregunta : idPreguntas) {
            List<String> respuestasPregunta = new ArrayList<>();

            // Obtener respuestas para preguntas de opción múltiple
            String[] respuestaIdsOpcional = request.getParameterValues("pregunta_" + idPregunta);
            if (respuestaIdsOpcional != null) {
                respuestasPregunta.addAll(Arrays.asList(respuestaIdsOpcional));
            }

            // Obtener respuestas para preguntas de varias respuestas
            for (String respuestaId : request.getParameterMap().keySet()) {
                if (respuestaId.startsWith("pregunta_" + idPregunta + "_")) {
                    respuestasPregunta.add(request.getParameter(respuestaId));
                }
            }

            // Para preguntas abiertas
            String respuestaAbierta = request.getParameter("pregunta_" + idPregunta);
            if (respuestaAbierta != null) {
                respuestasPregunta.add(respuestaAbierta);
            }

            respuestas.put(idPregunta, respuestasPregunta);
        }

        // Procesar cada pregunta
        for (Integer idPregunta : idPreguntas) {
            List<String> respuestasUsuario = respuestas.get(idPregunta);

            if (respuestasUsuario == null || respuestasUsuario.isEmpty()) {
                System.out.println("No se recibieron respuestas para la pregunta: " + idPregunta);
                continue;
            }

            // Obtener las opciones correctas desde la base de datos
            List<String> opcionesCorrectas = respuestaDao.CompararRespuestas(idPregunta);

            if (opcionesCorrectas == null || opcionesCorrectas.isEmpty()) {
                System.out.println("No se encontraron opciones correctas para la pregunta: " + idPregunta);
                continue;
            }

            // Verificar si alguna de las respuestas enviadas es correcta
            boolean esCorrecto = opcionesCorrectas.equals(respuestasUsuario);

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

