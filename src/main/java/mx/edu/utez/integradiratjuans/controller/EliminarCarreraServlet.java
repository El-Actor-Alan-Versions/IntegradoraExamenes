package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.CarreraDao;

import java.io.IOException;

@WebServlet("/Admin/eliminarCarreraServlet")
public class EliminarCarreraServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCarrera = Integer.parseInt(request.getParameter("id_carrera"));

        CarreraDao carreraDao = new CarreraDao();
        boolean isDeleted = carreraDao.delete(idCarrera);

        if (isDeleted) {
            response.sendRedirect("carrerasRegistradas.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
