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
        String nombre_carrera = request.getParameter("nombre_carrera"); // Cambio de nombre aquí
        Integer id_division = Integer.parseInt(request.getParameter("id_division"));


        Carrera carrera = new Carrera();
        carrera.setNombre_carrera(nombre_carrera);
        carrera.setId_division(id_division);


        CarreraDao dao = new CarreraDao();
        boolean update = dao.insert(carrera);

        if (update) {
            response.sendRedirect("verCarreras.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

}
