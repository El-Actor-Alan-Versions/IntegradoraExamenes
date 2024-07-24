package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateUsuarioEstadoServlet", urlPatterns = {"/Admin/updateUsuarioEstado"})
public class UpdateUsuarioEstadoServlet extends HttpServlet {

    private AlumnoDao alumnoDao = new AlumnoDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la matrícula del usuario
        String matricula = request.getParameter("matricula");
        String estado = request.getParameter("estado");

        // Intentar actualizar el estado del usuario
        boolean updateSuccess = false;
        try {
            alumnoDao.updateEstado(matricula, estado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (updateSuccess) {
            request.getSession().setAttribute("mensaje", "Usuario actualizado exitosamente.");
        } else {
            request.getSession().setAttribute("mensaje", "No se pudo actualizar el usuario.");
        }

        response.sendRedirect("gestionUsuario.jsp");
    }
}
