package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.OpcionesDao;
import mx.edu.utez.integradiratjuans.dao.PreguntaDao;
import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Preguntas;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/Alumno/CargarExamen")
public class PintarExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idExamenStr = request.getParameter("id_examen");

        if (idExamenStr == null || idExamenStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del examen no proporcionado.");
            return;
        }

        int idExamen;
        try {
            idExamen = Integer.parseInt(idExamenStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID del examen inválido.");
            return;
        }

        PreguntaDao preguntasDao = new PreguntaDao();
        OpcionesDao opcionesDao = new OpcionesDao();

        List<Preguntas> preguntas = preguntasDao.obtenerPreguntasPorExamen(idExamen);

        if (preguntas == null || preguntas.isEmpty()) {
            request.setAttribute("mensaje", "No hay preguntas disponibles para este examen.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        // Guardar los IDs de las preguntas en la sesión
        List<Integer> idsPreguntas = preguntas.stream()
                .map(Preguntas::getIdPregunta)
                .collect(Collectors.toList());
        session.setAttribute("idPreguntas", idsPreguntas);
        session.setAttribute("idExamen", idExamen); // Guardar idExamen en sesión

        for (Preguntas pregunta : preguntas) {
            List<Opcion> opciones = opcionesDao.obtenerOpcionesPorPregunta(pregunta.getIdPregunta());
            pregunta.setOpciones(opciones);
        }

        request.setAttribute("preguntas", preguntas);

        request.getRequestDispatcher("responderExamen.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method GET is not supported.");
    }
}
