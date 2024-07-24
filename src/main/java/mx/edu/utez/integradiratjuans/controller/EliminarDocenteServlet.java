package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.DocenteDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Admin/eliminarDocenteServlet")
public class EliminarDocenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DocenteDao docenteDao = new DocenteDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String nuevoEstado = "baja"; // El estado de baja

        try {
            docenteDao.updateEstado(matricula, nuevoEstado);
            response.sendRedirect("usuariosRegistrados.jsp"); // Redirige a la página de usuarios registrados
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirige a una página de error
        }
    }
}
