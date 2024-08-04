package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.model.Opcion;
import mx.edu.utez.integradiratjuans.model.Preguntas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Docente/VistaPreviaServlet")
public class VistaPreviaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Preguntas> preguntas = new ArrayList<>();

        // Recuperar el ID del examen desde la sesión
        HttpSession session = request.getSession();
        Integer idExamen = (Integer) session.getAttribute("idExamen");

        if (idExamen == null) {
            request.setAttribute("errorMessage", "ID del examen no encontrado en la sesión.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("idExamen", idExamen);

        int questionIndex = 0;
        while (request.getParameter("questions[" + questionIndex + "].pregunta") != null) {
            String preguntaTexto = request.getParameter("questions[" + questionIndex + "].pregunta");
            String questionType = request.getParameter("questions[" + questionIndex + "].questionType");

            if (preguntaTexto == null || questionType == null) {
                request.setAttribute("errorMessage", "Pregunta o tipo de pregunta no encontrados.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            Preguntas pregunta = new Preguntas(preguntaTexto, questionType);

            // Manejo de opciones y verificación de respuestas correctas
            if ("opcion_multiple".equals(questionType) || "varias_respuestas".equals(questionType)) {
                int optionIndex = 1;
                while (true) {
                    String opcionTexto = request.getParameter("questions[" + questionIndex + "].option" + optionIndex);
                    if (opcionTexto == null) {
                        break; // Termina el bucle si ya no hay más opciones
                    }

                    // Obtener el valor del checkbox para determinar si es la respuesta correcta
                    String isCorrectParam = request.getParameter("questions[" + questionIndex + "].correctOption" + optionIndex);
                    boolean isCorrect = "on".equals(isCorrectParam); // Comparar con "on" o cualquier otro valor esperado

                    // Imprimir para depuración
                    System.out.println("Valor del checkbox (correctOption" + optionIndex + "): " + isCorrectParam);
                    System.out.println("Es correcta: " + isCorrect);

                    pregunta.addOpcion(new Opcion(opcionTexto, isCorrect));
                    optionIndex++;
                }
            } else if ("abierta".equals(questionType)) {
                String respuesta = request.getParameter("questions[" + questionIndex + "].openEndedAnswer");
                if (respuesta != null) {
                    pregunta.setRespuesta(respuesta);
                }
            } else if ("verdadero_falso".equals(questionType)) {
                String correctAnswer = request.getParameter("questions[" + questionIndex + "].correctOption");
                if (correctAnswer != null) {
                    pregunta.addOpcion(new Opcion("Verdadero", "true".equals(correctAnswer)));
                    pregunta.addOpcion(new Opcion("Falso", "false".equals(correctAnswer)));
                }
            }

            preguntas.add(pregunta);
            questionIndex++;
        }

        request.setAttribute("preguntas", preguntas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vistaPrevia.jsp");
        dispatcher.forward(request, response);
    }

}
