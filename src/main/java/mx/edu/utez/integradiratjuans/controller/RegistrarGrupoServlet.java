package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.GrupoDao;
import mx.edu.utez.integradiratjuans.model.Grupo;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Admin/registrarGrupoServlet")
public class RegistrarGrupoServlet extends HttpServlet {

    private final GrupoDao grupoDao = new GrupoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gradoGrupo = request.getParameter("gradoGrupo");
        int idCarrera = Integer.parseInt(request.getParameter("idCarrera"));

        Grupo grupo = new Grupo();
        grupo.setGradoGrupo(gradoGrupo);
        grupo.setIdCarrera(idCarrera);

        try {
            grupoDao.insert(grupo);
            response.sendRedirect("gruposRegistrado.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
