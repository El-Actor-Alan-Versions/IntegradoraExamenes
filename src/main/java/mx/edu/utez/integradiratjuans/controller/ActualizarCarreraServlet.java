package mx.edu.utez.integradiratjuans.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.CarreraDao;
import mx.edu.utez.integradiratjuans.model.Carrera;

import java.io.IOException;

@WebServlet(name = "ActualizarCarreraServlet", urlPatterns = "/Admin/actualizarCarrreraServlet")
public class ActualizarCarreraServlet extends HttpServlet{
    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_carrera = Integer.parseInt(request.getParameter("id_carrera"));
        String nombre_carrera = request.getParameter("nombre_carrera");
        int idDivision = Integer.parseInt(request.getParameter("idDivision"));

        Carrera carrera = new Carrera(id_carrera, nombre_carrera, idDivision);
        CarreraDao dao = new CarreraDao();

        boolean update = dao.update(carrera);

        if (update){
            response.sendRedirect("verCarreras.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
