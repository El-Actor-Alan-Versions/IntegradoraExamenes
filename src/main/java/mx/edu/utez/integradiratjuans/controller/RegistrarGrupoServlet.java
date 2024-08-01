package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.GrupoDao;
import mx.edu.utez.integradiratjuans.model.Grupo;

import java.io.IOException;


@WebServlet("/Admin/registrarGrupoServlet")
public class RegistrarGrupoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gradoGrupo = request.getParameter("gradoGrupo");
        int idCarrera  = Integer.parseInt(request.getParameter("idCarrera"));

        Grupo grupo = new Grupo();
        grupo.setGradoGrupo(gradoGrupo);
        grupo.setIdCarrera(idCarrera);

        GrupoDao grupoDao = new GrupoDao();
        boolean inserted = grupoDao.insert(grupo);

        if (inserted){
            response.sendRedirect("verGrupos.jsp");
        } else {
            response.sendRedirect("erro.jsp");
        }


    }
}
