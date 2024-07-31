package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.CarreraDao;
import mx.edu.utez.integradiratjuans.model.Carrera;


import java.io.IOException;

@WebServlet("/Admin/registrarCarreraServlet")
public class RegistrarCarreraServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCarrera = Integer.parseInt(request.getParameter("idCarrera"));
        String nombre_carrera = request.getParameter("nombreCarrera"); // Cambio de nombre aquí
        int idDivision = Integer.parseInt(request.getParameter("idDivision"));



        Carrera carrera = new Carrera(idCarrera, nombre_carrera, idDivision);
        CarreraDao dao = new CarreraDao();

        boolean update = dao.update(carrera);

        if (update) {
            response.sendRedirect("verCarreras.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

}
