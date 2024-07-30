package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.dao.ClaseDao;
import mx.edu.utez.integradiratjuans.model.Examen;
import mx.edu.utez.integradiratjuans.model.Clase;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Docente/VerExamenesServlet")
public class VerExamenesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la matrícula del docente desde la sesión
        String matricula = (String) request.getSession().getAttribute("matriculaDocente");

        ExamenDao examenDao = new ExamenDao();
        ClaseDao claseDao = new ClaseDao();

        // Verifica si la matrícula es null y maneja el caso
        if (matricula == null) {
            request.setAttribute("error", "No se pudo obtener la matrícula del docente.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            // Obtener todas las clases del docente
            List<Clase> clases = claseDao.getClasesByDocente(matricula);
            request.getSession().setAttribute("clases", clases);

            // Obtener todos los exámenes
            List<Examen> todosExamenes = examenDao.getAll();
            Timestamp ahora = new Timestamp(System.currentTimeMillis());

            // Filtrar exámenes que no han caducado
            List<Examen> examenesVigentes = new ArrayList<>();
            for (Examen examen : todosExamenes) {
                Timestamp fechaCierre = examen.getFecha_cierre();

                boolean esNoCaducado = fechaCierre == null || fechaCierre.after(ahora);

                if (esNoCaducado) {
                    examenesVigentes.add(examen);
                }
            }

            request.setAttribute("examenes", examenesVigentes);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Se produjo un error al recuperar los exámenes.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("VerExamenes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la matrícula del docente desde la sesión
        String matricula = (String) request.getSession().getAttribute("matriculaDocente");

        ExamenDao examenDao = new ExamenDao();
        ClaseDao claseDao = new ClaseDao();

        // Verifica si la matrícula es null y maneja el caso
        if (matricula == null) {
            request.setAttribute("error", "No se pudo obtener la matrícula del docente.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        try {
            List<Clase> clases = claseDao.getClasesByDocente(matricula);
            request.getSession().setAttribute("clases", clases);

            // Obtener el parámetro id_clase del request (si existe)
            String idClaseParam = request.getParameter("id_clase");
            Integer idClase = (idClaseParam != null && !idClaseParam.isEmpty()) ? Integer.parseInt(idClaseParam) : null;

            // Obtener todos los exámenes
            List<Examen> todosExamenes = examenDao.getAll();
            Timestamp ahora = new Timestamp(System.currentTimeMillis());

            // Filtrar exámenes que no han caducado y por id_clase (si se ha especificado)
            List<Examen> examenesVigentes = new ArrayList<>();
            for (Examen examen : todosExamenes) {
                Timestamp fechaCierre = examen.getFecha_cierre();

                boolean esNoCaducado = fechaCierre == null || fechaCierre.after(ahora);
                boolean coincideConClase = idClase == null || idClase.equals(examen.getId_clase());

                if (esNoCaducado && coincideConClase) {
                    examenesVigentes.add(examen);
                }
            }

            request.setAttribute("examenes", examenesVigentes);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Se produjo un error al filtrar los exámenes.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("VerExamenes.jsp").forward(request, response);
    }
}
