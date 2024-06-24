package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.UsuarioDao;
import mx.edu.utez.integradiratjuans.model.Usuario;

import java.io.IOException;

@WebServlet(name="UsuarioServlet", value="/login")
public class UsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre_usuario = req.getParameter("nombre_usuario");
        String contra = req.getParameter("contra");

        // Conectar a la base de datos y buscar al usuario por nombre de usuario y contraseña
        UsuarioDao dao = new UsuarioDao();
        Usuario usuario = dao.getOne(nombre_usuario, contra);

        // En el método doPost del servlet UsuarioServlet
        if (usuario == null) {
            // Si el usuario no existe en la base de datos, establecer mensaje de error en la sesión
            HttpSession sesion = req.getSession();
            sesion.setAttribute("mensaje", "El usuario no existe en la BD");

            resp.sendRedirect("index.jsp"); // Redirigir a la página de inicio de sesión
        } else {
            // Si el usuario existe, determinar a qué página redirigir según el tipo de usuario
            int tipoUsuario = usuario.getTipo_usuario();
            String ruta;

            if (tipoUsuario == 1) {
                ruta = "indexAdmin.jsp"; // Redirigir a la página del administrador
            } else if (tipoUsuario == 2) {
                ruta = "indexDocente.jsp"; // Redirigir a la página del docente
            } else {
                ruta = "index.jsp"; // Puedes manejar otro tipo de usuarios aquí
            }

            // Guardar usuario en la sesión si es necesario
            HttpSession sesion = req.getSession();
            sesion.setAttribute("usuario", usuario);

            // Redireccionar a la página correspondiente
            resp.sendRedirect(ruta);
        }
    }
}
