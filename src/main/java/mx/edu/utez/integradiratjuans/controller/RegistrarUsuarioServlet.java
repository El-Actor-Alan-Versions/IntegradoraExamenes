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

@WebServlet(name="RegistrarUsuarioServlet", value="/sign_in")
public class RegistrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuario = req.getParameter("nombre_usuario");
        String pass1 = req.getParameter("pass1");
        String pass2 = req.getParameter("pass2");
        String correo = req.getParameter("correo_usuario");
        int tipoUsuario = Integer.parseInt(req.getParameter("tipo_usuario"));
        String ruta = "index.jsp";

        HttpSession sesion = req.getSession();

        if (!pass1.equals(pass2)) {
            sesion.setAttribute("mensaje2", "Las contraseñas no coinciden");
            resp.sendRedirect("registrarUsuario.jsp");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre_usuario(nombreUsuario);
        usuario.setContra(pass1);
        usuario.setCorreo(correo);
        usuario.setTipo_usuario(tipoUsuario);
        usuario.setEstado(true);

        UsuarioDao dao = new UsuarioDao();
        boolean insertado = dao.insert(usuario);

        if (insertado) {
            sesion.setAttribute("mensaje2", "Bien");
            resp.sendRedirect(ruta);
        } else {
            sesion.setAttribute("mensaje3", "El usuario no se registró correctamente");
            resp.sendRedirect("registrarUsuario.jsp");
        }
    }
}
