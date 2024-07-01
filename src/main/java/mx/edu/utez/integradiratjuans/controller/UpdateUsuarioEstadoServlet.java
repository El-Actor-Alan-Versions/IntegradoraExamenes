package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.UsuarioDao;

import java.io.IOException;

@WebServlet(name = "UpdateUsuarioEstadoServlet", urlPatterns = {"/updateUsuarioEstado"})
public class UpdateUsuarioEstadoServlet extends HttpServlet {

    private UsuarioDao usuarioDao = new UsuarioDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID del usuario
        int userId = Integer.parseInt(request.getParameter("id"));

        // Intentar actualizar el estado del usuario
        boolean updateSuccess = usuarioDao.updateEstado(userId, false);

        if (updateSuccess) {
            request.getSession().setAttribute("mensaje", "Usuario actualizado exitosamente.");
        } else {
            request.getSession().setAttribute("mensaje", "No se pudo actualizar el usuario.");
        }

        response.sendRedirect("gestionUsuario.jsp");
    }
}
