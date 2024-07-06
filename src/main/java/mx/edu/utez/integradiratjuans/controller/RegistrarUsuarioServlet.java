package mx.edu.utez.integradiratjuans.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradiratjuans.dao.AdministradorDao;
import mx.edu.utez.integradiratjuans.dao.AlumnoDao;
import mx.edu.utez.integradiratjuans.dao.DocenteDao;
import mx.edu.utez.integradiratjuans.model.Administrador;
import mx.edu.utez.integradiratjuans.model.Alumno;
import mx.edu.utez.integradiratjuans.model.Docente;

import java.io.IOException;

@WebServlet(name = "RegistrarUsuarioServlet", value = "/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener datos del formulario
        String tipoUsuario = req.getParameter("tipoUsuario");
        String matricula = req.getParameter("matricula");
        String nombre = req.getParameter("nombre");
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String correo = req.getParameter("correo");
        String contrasena = req.getParameter("contrasena");

        // Dependiendo del tipo de usuario, insertarlo en la tabla correspondiente
        switch (tipoUsuario) {
            case "administrador":
                Administrador admin = new Administrador(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, contrasena);
                AdministradorDao adminDao = new AdministradorDao();
                adminDao.insert(admin);
                break;
            case "alumno":
                String idGrupo = req.getParameter("id_grupo"); // Asegúrate de tener este campo en el formulario para los alumnos
                Alumno alumno = new Alumno(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, contrasena, Integer.parseInt(idGrupo));
                AlumnoDao alumnoDao = new AlumnoDao();
                alumnoDao.insert(alumno);
                break;
            case "docente":
                Docente docente = new Docente(matricula, nombre, apellidoPaterno, apellidoMaterno, correo, contrasena);
                DocenteDao docenteDao = new DocenteDao();
                docenteDao.insert(docente);
                break;
            default:
                throw new ServletException("Tipo de usuario desconocido: " + tipoUsuario);
        }

        // Redirigir a una página de éxito o de nuevo al formulario con un mensaje de éxito
        resp.sendRedirect("exito.jsp");
    }
}
