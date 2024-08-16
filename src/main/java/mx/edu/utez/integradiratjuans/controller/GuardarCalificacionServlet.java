package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.CalificacionDao;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/Docente/guardarCalificacion")
public class GuardarCalificacionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Variable para acumular la suma de los 1
        int sumaCorrectas = 0;

        HttpSession session = request.getSession();
        Integer idExamen = (Integer) session.getAttribute("idExamen"); // Ajuste aquí
        if (idExamen == null) {
            response.sendRedirect("error.jsp"); // Redirige o maneja el error
            return;
        }

        // Variable para contar el total de preguntas
        int totalPreguntas = 0;

        // Obtener todos los parámetros del request
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // Verificar si el parámetro es una calificación
            if (paramName.startsWith("calificacion_")) {
                String preguntaId = paramName.substring("calificacion_".length());
                String calificacion = request.getParameter(paramName);
                String respuestaId = request.getParameter("pregunta_" + preguntaId);

                // Incrementar el contador de preguntas
                totalPreguntas++;

                // Convertir la calificación a un entero
                int calificacionInt = Integer.parseInt(calificacion);

                // Sumar la calificación si es 1
                if (calificacionInt == 1) {
                    sumaCorrectas++;
                }

                // Aquí podrías insertar la calificación en la base de datos o hacer lo que necesites

                System.out.println("Calificación: " + calificacionInt);
            }
        }

        // Mostrar la suma total de respuestas correctas
        System.out.println("Total de respuestas correctas: " + sumaCorrectas);

        // Mostrar el total de preguntas
        System.out.println("Total de preguntas: " + totalPreguntas);

        double calificacion = (sumaCorrectas / (double) totalPreguntas) * 100;
        System.out.println(calificacion);
        System.out.println("El id de la calificacion es " + idExamen);

        CalificacionDao calificacionDao = new CalificacionDao();
        calificacionDao.updateCalificaciones(calificacion, idExamen);

        // Redirigir después de guardar las calificaciones
        response.sendRedirect("exito.jsp");
    }
}
