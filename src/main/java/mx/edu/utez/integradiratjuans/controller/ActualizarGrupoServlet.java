package mx.edu.utez.integradiratjuans.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.GrupoDao;
import mx.edu.utez.integradiratjuans.model.Grupo;

import java.io.IOException;

@WebServlet ("/Admin/actualizarGrupoServlet")
public class ActualizarGrupoServlet  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));
        String gradoGrupo = request.getParameter("gradoGrupo");
        int idCarrera = Integer.parseInt(request.getParameter("idCarrera"));


        Grupo grupo = new Grupo();
        grupo.setIdGrupo(idGrupo);
        grupo.setGradoGrupo(gradoGrupo);
        grupo.setIdCarrera(idCarrera);

        GrupoDao grupoDao = new GrupoDao();

        boolean update = grupoDao.update(grupo);

        if (update){
            response.sendRedirect("verGrupos.jsp");
        } else{
            response.sendRedirect("error.jsp");
        }
    }
}
