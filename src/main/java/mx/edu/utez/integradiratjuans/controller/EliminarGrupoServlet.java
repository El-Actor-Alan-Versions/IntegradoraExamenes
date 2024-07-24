package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.GrupoDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Admin/eliminarGrupoServlet")
public class EliminarGrupoServlet extends HttpServlet {

    private final GrupoDao grupoDao = new GrupoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idGrupo = Integer.parseInt(request.getParameter("idGrupo"));

        try {
            grupoDao.delete(idGrupo);  // Asumiendo que tienes un método delete en GrupoDao
            response.sendRedirect("gruposRegistrados.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
