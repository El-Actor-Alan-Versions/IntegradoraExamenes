package mx.edu.utez.integradiratjuans.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.ClaseDao;
import mx.edu.utez.integradiratjuans.model.Clase;

import java.io.IOException;

@WebServlet ("/Admin/actualizarClaseServlet")
public class ActualizarClaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_clase = Integer.parseInt(request.getParameter("id_clase"));
        int id_grupo   = Integer.parseInt(request.getParameter("id_grupo"));
        int id_materia = Integer.parseInt(request.getParameter("id_materia"));
        String matricula =  request.getParameter("matricula");


        Clase clase = new Clase();
        clase.setId_clase(id_clase);
        clase.setId_grupo(id_grupo);
        clase.setId_materia(id_materia);
        clase.setMatricula(matricula);


        ClaseDao claseDao = new ClaseDao();

        boolean update = claseDao.update(clase);

        if (update){
            response.sendRedirect("verClases.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }





    }
}
