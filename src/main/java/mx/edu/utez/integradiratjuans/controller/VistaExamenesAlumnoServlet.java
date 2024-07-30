package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.ClaseDao;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.model.Clase;
import mx.edu.utez.integradiratjuans.model.Examen;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/Alumno/VerExamenesAlumnoServlet")
public class VistaExamenesAlumnoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID de la clase desde la sesión
        int idClase = (Integer) request.getSession().getAttribute("clase");
        System.out.println(idClase);
        // Verifica si el ID de clase es válido
        if (idClase <= 0) {
            request.setAttribute("error", "No se pudo obtener un ID de clase válido.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        ExamenDao examenDao = new ExamenDao();

        try {
            // Obtener todos los exámenes
            List<Examen> todosExamenes = examenDao.getExamenesPorClase(idClase);
            Timestamp ahora = new Timestamp(System.currentTimeMillis());

            // Filtrar exámenes que no han caducado
            List<Examen> examenesVigentes = new ArrayList<>();
            for (Examen examen : todosExamenes) {
                Timestamp fechaCierre = examen.getFecha_cierre();
                if (fechaCierre == null || fechaCierre.after(ahora)) {
                    examenesVigentes.add(examen);
                }
            }

            // Establecer los exámenes vigentes como atributo de solicitud
            request.setAttribute("examenes", examenesVigentes);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Se produjo un error al recuperar los exámenes.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Redirigir a la página JSP para mostrar los exámenes
        request.getRequestDispatcher("verExamenes.jsp").forward(request, response);
    }
}
