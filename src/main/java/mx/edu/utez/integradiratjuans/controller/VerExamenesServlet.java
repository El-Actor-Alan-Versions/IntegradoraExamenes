package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.ExamenDao;
import mx.edu.utez.integradiratjuans.model.Examen;

import java.io.IOException;
import java.util.List;

@WebServlet("/verExamenes")
public class VerExamenesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExamenDao dao = new ExamenDao();
        List<Examen> examenes = dao.getAll(); // Obtener la lista de exámenes desde el DAO
        request.setAttribute("examenes", examenes); // Enviar la lista a la JSP
        request.getRequestDispatcher("verExamenes.jsp").forward(request, response); // Redirigir a la JSP
    }
}
