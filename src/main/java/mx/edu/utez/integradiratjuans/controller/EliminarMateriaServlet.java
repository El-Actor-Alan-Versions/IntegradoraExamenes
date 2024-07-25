package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.MateriaDao;


import java.io.IOException;

@WebServlet("/Admin/eliminarMateriaServlet")
public class EliminarMateriaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID de la materia desde el formulario
        int idMateria = Integer.parseInt(request.getParameter("idMateria"));

        // Eliminar la materia en la base de datos
        MateriaDao materiaDao = new MateriaDao();
        boolean resultado = materiaDao.delete(idMateria);

        // Redirigir según el resultado de la operación
        if (resultado) {
            response.sendRedirect("exito.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
