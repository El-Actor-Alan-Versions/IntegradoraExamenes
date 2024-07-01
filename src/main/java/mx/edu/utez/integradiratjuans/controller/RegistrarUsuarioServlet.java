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

@WebServlet(name="RegistrarUsuarioServlet", value = "/sign_in")
public class RegistrarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operacion = req.getParameter("operacion");

        if (operacion.equals("registrar")) {
            registrarUsuario(req, resp);
        } else {
            actualizarUsuario(req, resp);
        }
    }

    private void registrarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuario = req.getParameter("nombre_usuario");
        String pass1 = req.getParameter("pass1");
        String pass2 = req.getParameter("pass2");
        String correo = req.getParameter("correo_usuario");
        String tipoUsuarioStr = req.getParameter("tipo_usuario");
        String ruta = "index.jsp";

        HttpSession sesion = req.getSession();

        if (!pass1.equals(pass2)) {
            sesion.setAttribute("mensaje2", "Las contraseñas no coinciden");
            resp.sendRedirect("registrarUsuario.jsp");
            return;
        }

        int tipoUsuario;
        try {
            tipoUsuario = Integer.parseInt(tipoUsuarioStr);
        } catch (NumberFormatException e) {
            sesion.setAttribute("mensaje2", "Tipo de usuario no válido");
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

    private void actualizarUsuario(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre_usuario = req.getParameter("nombre_usuario");
        String pass = req.getParameter("pass");
        String correo = req.getParameter("correo");
        String tipo_usuarioStr = req.getParameter("tipo_usuario");
        String idStr = req.getParameter("id");

        int tipo_usuario;
        int id;
        try {
            tipo_usuario = Integer.parseInt(tipo_usuarioStr);
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("mensaje", "Datos de usuario no válidos");
            resp.sendRedirect("index.jsp");
            return;
        }

        Usuario u = new Usuario();
        u.setNombre_usuario(nombre_usuario);
        u.setContra(pass);
        u.setCorreo(correo);
        u.setTipo_usuario(tipo_usuario);
        u.setId(id);

        UsuarioDao dao = new UsuarioDao();
        if (dao.update(u)) {
            resp.sendRedirect("gestionUsuario.jsp");
        } else {
            req.getSession().setAttribute("mensaje", "No se pudo actualizar");
            resp.sendRedirect("index.jsp");
        }
    }
}
