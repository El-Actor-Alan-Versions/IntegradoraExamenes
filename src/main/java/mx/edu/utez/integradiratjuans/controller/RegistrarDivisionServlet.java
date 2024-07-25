package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.DivisionDao;
import mx.edu.utez.integradiratjuans.model.Division;


import java.io.IOException;

@WebServlet(name = "RegistrarDivisionServlet", urlPatterns = {"/registrarDivisionServlet"})
public class RegistrarDivisionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreDivision = request.getParameter("nombreDivision");

        Division division = new Division();
        division.setNombreDivision(nombreDivision);

        DivisionDao dao = new DivisionDao();
        boolean inserted = dao.insert(division);

        if (inserted) {
            response.sendRedirect("divisiones.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
