package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.OpcionesDao;
import mx.edu.utez.integradiratjuans.dao.PreguntaDao;
import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Preguntas;

import java.io.IOException;
import java.util.List;

@WebServlet("/Alumno/CargarExamen")
public class PintarExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idExamenStr = request.getParameter("id_examen");
        int idExamen = Integer.parseInt(idExamenStr);

        System.out.println("ID del Examen: " + idExamen);

        PreguntaDao preguntasDao = new PreguntaDao();
        OpcionesDao opcionesDao = new OpcionesDao();

        List<Preguntas> preguntas = preguntasDao.obtenerPreguntasPorExamen(idExamen);
        System.out.println("Número de preguntas obtenidas: " + preguntas.size());

        // Verificar que las preguntas no estén vacías
        if (preguntas == null || preguntas.isEmpty()) {
            System.out.println("No se encontraron preguntas para el examen con ID: " + idExamen);
        }

        for (Preguntas pregunta : preguntas) {
            System.out.println("Obteniendo opciones para la pregunta ID: " + pregunta.getIdPregunta());
            List<Opcion> opciones = opcionesDao.obtenerOpcionesPorPregunta(pregunta.getIdPregunta());
            System.out.println("Número de opciones obtenidas para la pregunta ID " + pregunta.getIdPregunta() + ": " + opciones.size());

            pregunta.setOpciones(opciones);
        }

        request.setAttribute("preguntas", preguntas);
        request.setAttribute("idExamen", idExamen);

        request.getRequestDispatcher("responderExamen.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method GET is not supported.");
    }
}
