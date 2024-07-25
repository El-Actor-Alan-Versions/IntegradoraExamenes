package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.MateriaDao;
import mx.edu.utez.integradiratjuans.model.Materia;


import java.io.IOException;

@WebServlet("/Admin/registrarMateriaServlet")
public class RegistrarMateriaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombreMateria = request.getParameter("nombreMateria");

        // Crear un objeto Materia
        Materia materia = new Materia();
        materia.setNombreMateria(nombreMateria);

        // Insertar la materia en la base de datos
        MateriaDao materiaDao = new MateriaDao();
        boolean resultado = materiaDao.insert(materia);

        // Redirigir según el resultado de la operación
        if (resultado) {
            response.sendRedirect("exito.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
