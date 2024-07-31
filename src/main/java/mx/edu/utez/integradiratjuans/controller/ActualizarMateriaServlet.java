package mx.edu.utez.integradiratjuans.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.MateriaDao;
import mx.edu.utez.integradiratjuans.model.Materia;

import java.io.IOException;
@WebServlet(name = "ActualizarMateriaServlet", urlPatterns = {"/Admin/ActualizarMateriaServlet"})
public class ActualizarMateriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idMateria = Integer.parseInt(request.getParameter("idMateria"));
        String nombreMateria = request.getParameter("nombreMateria");

        Materia materia = new Materia(idMateria, nombreMateria);
        MateriaDao dao = new MateriaDao();

        boolean update = dao.update(materia);

        if(update){
            response.sendRedirect("verMaterias.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

}
