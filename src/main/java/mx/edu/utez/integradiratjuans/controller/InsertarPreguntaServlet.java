package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.OpcionesDao;
import mx.edu.utez.integradiratjuans.dao.PreguntaDao;
import mx.edu.utez.integradiratjuans.dao.RespuestaDao;
import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Pregunta;
import mx.edu.utez.integradiratjuans.model.Respuesta;

import java.io.IOException;

@WebServlet("/insertarPregunta")
public class InsertarPreguntaServlet extends HttpServlet {
    private PreguntaDao preguntaDao;
    private RespuestaDao respuestaDao;
    private OpcionesDao opcionesDao;

    public InsertarPreguntaServlet() {
        this.preguntaDao = new PreguntaDao();
        this.respuestaDao = new RespuestaDao();
        this.opcionesDao = new OpcionesDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipoPregunta");
        String textoPregunta = request.getParameter("textoPregunta");
        int idExamen = Integer.parseInt(request.getParameter("idExamen"));  // Suponiendo que tienes este valor en el formulario

        Pregunta pregunta = new Pregunta();
        pregunta.setPregunta(textoPregunta);
        pregunta.setIdExamen(idExamen);

        boolean isPreguntaInserted = preguntaDao.insert(pregunta);

        if (isPreguntaInserted) {
            // Obtener el ID de la última pregunta insertada
            int idPregunta = pregunta.getIdPregunta();

            if (tipo.equals("opcion_multiple") || tipo.equals("varias_respuestas")) {
                String[] opciones = request.getParameterValues("textoOpcion");
                String[] correctas = request.getParameterValues("opcionCorrecta");

                for (int i = 0; i < opciones.length; i++) {
                    Opcion opcion = new Opcion();
                    opcion.setOpcion(opciones[i]);
                    opcion.setIdPregunta(idPregunta);
                    opcion.setCorrecta(correctas != null && correctas[i].equals("on"));
                    opcionesDao.insert(opcion);
                }
            } else if (tipo.equals("respuesta_abierta")) {
                String textoRespuesta = request.getParameter("textoRespuestaAbierta");
                Respuesta respuesta = new Respuesta();
                respuesta.setRespuesta(textoRespuesta);
                respuesta.setIdPregunta(idPregunta);
                respuestaDao.insert(respuesta);
            }

            response.sendRedirect("vistaPrevia.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
