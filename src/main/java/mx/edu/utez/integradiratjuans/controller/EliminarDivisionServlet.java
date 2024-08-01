package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.DivisionDao;


import java.io.IOException;

@WebServlet("/Admin/eliminarDivisionServlet")
public class EliminarDivisionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID de la división desde el formulario
        int idDivision = Integer.parseInt(request.getParameter("idDivision"));

        // Eliminar la división en la base de datos
        DivisionDao divisionDao = new DivisionDao();
        boolean resultado = divisionDao.delete(idDivision);

        // Redirigir según el resultado de la operación
        if (resultado) {
            response.sendRedirect("verDivisiones.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
