package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.DivisionDao;
import mx.edu.utez.integradiratjuans.model.Division;


import java.io.IOException;

@WebServlet(name = "ActualizarDivisionServlet", urlPatterns = {"/Admin/actualizarDivisionServlet"})
public class ActualizarDivisionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idDivision = Integer.parseInt(request.getParameter("idDivision"));
        String nombreDivision = request.getParameter("nombreDivision");

        Division division = new Division(idDivision, nombreDivision);
        DivisionDao dao = new DivisionDao();

        boolean updated = dao.update(division);

        if (updated) {
            response.sendRedirect("verDivisiones.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
