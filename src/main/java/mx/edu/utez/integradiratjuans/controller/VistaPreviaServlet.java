package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Pregunta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/Docente/VistaPreviaServlet")
public class VistaPreviaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pregunta> preguntas = new ArrayList<>();

        int questionIndex = 0;
        while (request.getParameter("questions[" + questionIndex + "].pregunta") != null) {
            String preguntaTexto = request.getParameter("questions[" + questionIndex + "].pregunta");
            String questionType = request.getParameter("questions[" + questionIndex + "].questionType");
            Pregunta pregunta = new Pregunta(preguntaTexto, questionType);

            if (questionType.equals("multiple_choice") || questionType.equals("multiple_answers")) {
                int optionIndex = 1;
                while (request.getParameter("questions[" + questionIndex + "].option" + optionIndex) != null) {
                    String opcionTexto = request.getParameter("questions[" + questionIndex + "].option" + optionIndex);
                    boolean isCorrect = request.getParameter("questions[" + questionIndex + "].correctOption" + optionIndex) != null;
                    pregunta.addOpcion(new Opcion(opcionTexto, isCorrect));
                    optionIndex++;
                }
            } else if (questionType.equals("open_ended")) {
                String respuesta = request.getParameter("questions[" + questionIndex + "].openEndedAnswer");
                pregunta.setRespuesta(respuesta);
            }

            preguntas.add(pregunta);
            questionIndex++;
        }

        request.setAttribute("preguntas", preguntas);
        request.getRequestDispatcher("vistaPrevia.jsp").forward(request, response);
    }
}
