package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.PreguntaDao;
import mx.edu.utez.integradiratjuans.model.Preguntas;
import mx.edu.utez.integradiratjuans.model.Opcion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Docente/InsertarPreguntaServlet")
public class InsertarPreguntaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreguntaDao preguntaDao = new PreguntaDao();

        // Recuperar el ID del examen desde la sesión
        HttpSession session = request.getSession();
        Integer idExamen = (Integer) session.getAttribute("idExamen");

        if (idExamen == null) {
            response.sendRedirect("crearExamen.jsp");
            return;
        }

        // Crear la lista de preguntas
        List<Preguntas> preguntas = new ArrayList<>();
        int index = 0;

        while (request.getParameter("questions[" + index + "].pregunta") != null) {
            String texto = request.getParameter("questions[" + index + "].pregunta");
            String tipo = request.getParameter("questions[" + index + "].questionType");
            String respuesta = tipo.equals("abierta") ? request.getParameter("questions[" + index + "].openEndedAnswer") : null;

            Preguntas pregunta = new Preguntas(texto, tipo);
            pregunta.setRespuesta(respuesta);
            pregunta.setIdExamen(idExamen);

            // Manejo de opciones para preguntas de opción múltiple y varias respuestas
            if ("opcion_multiple".equals(tipo) || "varias_respuestas".equals(tipo)) {
                int opcionIndex = 1;
                while (request.getParameter("questions[" + index + "].option" + opcionIndex) != null) {
                    String opcionTexto = request.getParameter("questions[" + index + "].option" + opcionIndex);
                    boolean esCorrecta = "true".equals(request.getParameter("questions[" + index + "].correctOption" + opcionIndex));

                    Opcion opcion = new Opcion();
                    opcion.setOpcion(opcionTexto);
                    opcion.setCorrecta(esCorrecta);

                    pregunta.addOpcion(opcion);
                    opcionIndex++;
                }
            }

            // Manejo de opciones para preguntas de verdadero o falso
            if ("verdadero_falso".equals(tipo)) {
                Opcion opcionVerdadero = new Opcion();
                opcionVerdadero.setOpcion("Verdadero");
                opcionVerdadero.setCorrecta("true".equals(request.getParameter("questions[" + index + "].correctOption1")));

                Opcion opcionFalso = new Opcion();
                opcionFalso.setOpcion("Falso");
                opcionFalso.setCorrecta("true".equals(request.getParameter("questions[" + index + "].correctOption2")));

                pregunta.addOpcion(opcionVerdadero);
                pregunta.addOpcion(opcionFalso);
                System.out.println("correctOption1: " + request.getParameter("questions[" + index + "].correctOption1"));
                System.out.println("correctOption2: " + request.getParameter("questions[" + index + "].correctOption2"));
                System.out.println("opcionVerdadero.isCorrecta(): " + opcionVerdadero.isCorrecta());
                System.out.println("opcionFalso.isCorrecta(): " + opcionFalso.isCorrecta());

            }



            preguntas.add(pregunta);
            index++;
        }

        // Insertar todas las preguntas en la base de datos
        boolean exito = true;
        for (Preguntas pregunta : preguntas) {
            if (!preguntaDao.insertarPregunta(pregunta)) {
                exito = false;
                break;
            }
        }

        if (exito) {
            response.sendRedirect("exito.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
