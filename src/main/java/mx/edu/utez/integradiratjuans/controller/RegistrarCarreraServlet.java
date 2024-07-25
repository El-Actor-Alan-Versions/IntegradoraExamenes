package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.CarreraDao;
import mx.edu.utez.integradiratjuans.model.Carrera;


import java.io.IOException;

@WebServlet("/registrarCarreraServlet")
public class RegistrarCarreraServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCarrera = request.getParameter("nombreCarrera");
        int idDivision = Integer.parseInt(request.getParameter("idDivision"));

        Carrera carrera = new Carrera();
        carrera.setNombre_carrera(nombreCarrera);
        carrera.setId_division(idDivision);

        CarreraDao carreraDao = new CarreraDao();
        boolean isInserted = carreraDao.insert(carrera);

        if (isInserted) {
            response.sendRedirect("exito.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
