package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.integradiratjuans.dao.AdministradorDao;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.dao.DocenteDao;
import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.model.Docente;

import java.io.IOException;

@WebServlet(name="UsuarioServlet", value="/login")
public class UsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuario = req.getParameter("nombre_usuario");
        String contra = req.getParameter("contra");

        // Validar credenciales para cada tipo de usuario
        AdministradorDao adminDao = new AdministradorDao();
        Administrador admin = adminDao.getOne(nombreUsuario, contra);

        AlumnoDao alumnoDao = new AlumnoDao();
        Alumno alumno = alumnoDao.getOne(nombreUsuario, contra);

        DocenteDao docenteDao = new DocenteDao();
        Docente docente = docenteDao.getOne(nombreUsuario, contra);

        String ruta = "index.jsp"; // Página por defecto

        if (admin != null) {
            // Si es administrador, redirigir a vista de administrador
            HttpSession session = req.getSession();
            session.setAttribute("usuario", admin);
            ruta = "indexAdmin.jsp";
        } else if (alumno != null) {
            // Si es alumno, redirigir a vista de alumno
            HttpSession session = req.getSession();
            session.setAttribute("usuario", alumno);
            ruta = "indexAlumno.jsp";
        } else if (docente != null) {
            // Si es docente, redirigir a vista de docente
            HttpSession session = req.getSession();
            session.setAttribute("usuario", docente);
            ruta = "indexDocente.jsp";
        } else {
            // Si no coincide con ningún usuario, mostrar mensaje de error y redirigir a login
            HttpSession session = req.getSession();
            session.setAttribute("mensaje", "Credenciales incorrectas");
        }

        resp.sendRedirect(ruta);
    }
}
